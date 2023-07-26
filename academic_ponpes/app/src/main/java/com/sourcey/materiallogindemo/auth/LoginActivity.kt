package com.sourcey.materiallogindemo.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sourcey.materiallogindemo.R
import com.sourcey.materiallogindemo.core.service.AuthService
import com.sourcey.materiallogindemo.databinding.ActivityLoginBinding
import com.sourcey.materiallogindemo.siswa.SiswaActivity
import com.sourcey.tenant.core.helper.Connection
import com.sourcey.tenant.core.helper.SessionManager
import com.facebook.*
import com.facebook.login.BuildConfig
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityLoginBinding
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var fiarebaseAuth: FirebaseAuth
    lateinit var sessionManager: SessionManager
    lateinit var authService: AuthService
    lateinit var connection: Connection
    lateinit var callbackManager: CallbackManager
    var RC_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        connection = Connection(this)
        sessionManager = SessionManager(this)
        authService = AuthService.create(this)
        callbackManager = CallbackManager.Factory.create()
        configureLoginWithGoogle()
        setOnClickListerner()
    }

    fun configureLoginWithGoogle(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    fun setOnClickListerner(){
        binding.btnLogin.setOnClickListener(this)
        binding.btnLoginWithGoogle.setOnClickListener(this)
        binding.btnLoginWithFb.setOnClickListener(this)
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }




    override fun onClick(v: View) {
        var itemId = v.id
        when(itemId){
            R.id.btn_login -> mGoogleSignInClient.signOut()
            R.id.btn_login_with_google -> signInGoogle()
            R.id.btn_login_with_fb -> loginFacebook()
        }
    }

    private fun loginFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile", "email"))

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                getUserProfile(result?.accessToken, result?.accessToken?.userId)
            }

            override fun onCancel() {
                Toast.makeText(this@LoginActivity, "Login Cancelled", Toast.LENGTH_LONG).show()

            }

            override fun onError(error: FacebookException) {
                Toast.makeText(this@LoginActivity, error.message, Toast.LENGTH_LONG).show()
            }


        })
    }

    @SuppressLint("LongLogTag")
    fun getUserProfile(token: AccessToken?, userId: String?) {

        val parameters = Bundle()
        parameters.putString(
            "fields",
            "id, first_name, middle_name, last_name, name, picture, email"
        )
        GraphRequest(token,
            "/$userId/",
            parameters,
            HttpMethod.GET,
            GraphRequest.Callback { response ->
                val jsonObject = response.jsonObject ?: return@Callback

                // Facebook Access Token
                // You can see Access Token only in Debug mode.
                // You can't see it in Logcat using Log.d, Facebook did that to avoid leaking user's access token.
                if (BuildConfig.DEBUG) {
                    FacebookSdk.setIsDebugEnabled(true)
                    FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
                }

                // Facebook Id
                if (jsonObject.has("id")) {
                    val facebookId = jsonObject.getString("id")
                    Log.i("Facebook Id: ", facebookId.toString())
                } else {
                    Log.i("Facebook Id: ", "Not exists")
                }


                // Facebook First Name
                if (jsonObject.has("first_name")) {
                    val facebookFirstName = jsonObject.getString("first_name")
                    Log.i("Facebook First Name: ", facebookFirstName)
                } else {
                    Log.i("Facebook First Name: ", "Not exists")
                }


                // Facebook Middle Name
                if (jsonObject.has("middle_name")) {
                    val facebookMiddleName = jsonObject.getString("middle_name")
                    Log.i("Facebook Middle Name: ", facebookMiddleName)
                } else {
                    Log.i("Facebook Middle Name: ", "Not exists")
                }


                // Facebook Last Name
                if (jsonObject.has("last_name")) {
                    val facebookLastName = jsonObject.getString("last_name")
                    Log.i("Facebook Last Name: ", facebookLastName)
                } else {
                    Log.i("Facebook Last Name: ", "Not exists")
                }


                // Facebook Name
                if (jsonObject.has("name")) {
                    val facebookName = jsonObject.getString("name")
                    Log.i("Facebook Name: ", facebookName)
                } else {
                    Log.i("Facebook Name: ", "Not exists")
                }


                // Facebook Profile Pic URL
                if (jsonObject.has("picture")) {
                    val facebookPictureObject = jsonObject.getJSONObject("picture")
                    if (facebookPictureObject.has("data")) {
                        val facebookDataObject = facebookPictureObject.getJSONObject("data")
                        if (facebookDataObject.has("url")) {
                            val facebookProfilePicURL = facebookDataObject.getString("url")
                            Log.i("Facebook Profile Pic URL: ", facebookProfilePicURL)
                        }
                    }
                } else {
                    Log.i("Facebook Profile Pic URL: ", "Not exists")
                }

                // Facebook Email
                if (jsonObject.has("email")) {
                    val facebookEmail = jsonObject.getString("email")
                    Log.i("Facebook Email: ", facebookEmail)
                } else {
                    Log.i("Facebook Email: ", "Not exists")
                }

                saveSign(firstname = jsonObject.getString("first_name"),
                        lastname = jsonObject.getString("last_name"),
                        username = jsonObject.getString("name"),
                        email = jsonObject.getString("email").replace("@", "%40"),
                        uid =  jsonObject.getString("id")
                    )

            }).executeAsync()
    }






    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.

            var displayName = account.displayName.toString().split(" ")
            var firstName = displayName.get(0)
            var lastname = displayName.get(1)
            var displayEmail = account.email.toString()
            var email = displayEmail.replace("@", "%40")
            Log.d("TAG", "data eee: "+ email)

            saveSign(firstName,
                lastname,
                account.givenName.toString(),
                email,
                account.id.toString()
            )

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("ERROR", "signInResult:failed code=" + e.statusCode)

        }
    }

    fun saveSign(firstname: String, lastname: String, username: String, email: String, uid: String){
        if (connection.isConnectionInternet()){
            authService.loginWithSosmed(firstname, lastname, username, email, uid).enqueue(object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful){
                        sessionManager.setIsLogin()
                        sessionManager.setFirstName(firstname)
                        sessionManager.setLastName(lastname)
                        sessionManager.setUsername(username)
                        sessionManager.setEmail(email)
                        sessionManager.setUid(uid)
                        startActivity(Intent(this@LoginActivity, SiswaActivity::class.java))
                        finishAffinity()
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("LOGIN", "onFailure: -->"+ t.message)
                }
            })
        }else{
            Toast.makeText(this, "Cek Koneksi Internet Anda", Toast.LENGTH_SHORT).show()
        }
    }
}