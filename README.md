# AryanVPN

Android VPN client based on [v2rayNG](https://github.com/2dust/v2rayNG) (Xray core).

- App name: **AryanVPN**
- Package: `com.aryan.vpn`
- Default servers: Aryan Server 1–10 (seeded on first launch)

## Build APK with GitHub Actions

1. Open **Actions** → **Build AryanVPN APK**
2. Click **Run workflow**
3. Download the artifact when finished

## Local build

Open the `V2rayNG` folder in Android Studio, or:

```bash
cd V2rayNG
./gradlew assemblePlaystoreDebug
```

APK will be under `app/build/outputs/apk/`.
