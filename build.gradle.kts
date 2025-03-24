buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.2") // Đảm bảo phiên bản này được cập nhật
    }
}

plugins {
    alias(libs.plugins.androidApplication) version "8.3.1" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}
