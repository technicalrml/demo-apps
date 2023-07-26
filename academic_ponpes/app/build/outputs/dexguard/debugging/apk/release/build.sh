#!/bin/sh

/Users/rml/Library/Java/JavaVirtualMachines/jbr-17.0.7/Contents/Home/bin/java \
	-Ddexguard.license=/Users/rml/Desktop/Rizky Pradana Putra/dexguard-license.txt \
	-Dfile.encoding=UTF-8 \
	-Duser.country=SG \
	-Duser.language=en \
	-Duser.variant \
	-cp \
	/Users/rml/Desktop/Rizky Pradana Putra/DexGuard Versi/DexGuard-9.4.13/lib/dexguard.jar \
	com.guardsquare.dexguard.aOt \
	-injars \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/apk/release/app-release.apk' \
	-include \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/configs/release/libraryjars.pro' \
	-include \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/configs/release/consumer-rules.pro' \
	-outjars \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/apk/release/app-release-protected.apk' \
	-printconfiguration \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/debugging/apk/release/fullconfig.pro' \
	-telemetrydata \
	gradle \
	7.4 \
	-telemetrydata \
	agp \
	7.3.0 \
	-telemetrydata \
	java \
	17 \
	-telemetrydata \
	jvm \
	17.0.7+7-b1000.6 \
	-protectionreportdirectory \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/reports/apk/release' \
	-intermediatedirectory,allowoverwrite \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/bitguard/apk/release' \
	-libraryjars \
	'/Users/rml/Library/Android/sdk/platforms/android-33/android.jar' \
	-include \
	/lib/dexguard-release.pro \
	-printmaindexlist \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/maindexlist.txt' \
	-printseeds \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/seeds.txt' \
	-printusage \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/usage.txt' \
	-printresourceusage \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/resourceusage.txt' \
	-printmapping \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/mapping.txt' \
	-printresourcefilenamemapping \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/resourcefilenamemapping.txt' \
	-printresourcemapping \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/resourcemapping.txt' \
	-printclassencryption \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/classencryption.txt' \
	-printstringencryption \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/stringencryption.txt' \
	-printassetencryption \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/assetencryption.txt' \
	-printresourceencryption \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/resourceencryption.txt' \
	-printnativelibraryencryption \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/nativelibraryencryption.txt' \
	-printinjectmapping \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/injectmapping.txt' \
	-printthreatcastmapping \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/threatcast-mapping.yml' \
	-printnatificationmapping \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/natificationmapping.txt' \
	-printcodepushmapping \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/build/outputs/dexguard/mapping/apk/release/codepushmapping.txt' \
	-keystore \
	'/Users/rml/Desktop/Rizky Pradana Putra/DexGuard Versi/DexGuard-9.4.13/samples/debug.keystore' \
	-keystorepassword \
	android \
	-keyalias \
	androiddebugkey \
	-keypassword \
	android \
	-apksignatureschemes \
	v1, v2 \
	-include \
	'/Users/rml/Desktop/Rizky Pradana Putra/Aplikasi/academic_ponpes/app/dexguard-project.txt'
