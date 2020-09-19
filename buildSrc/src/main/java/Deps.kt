object Versions {
    val compileSdkVersion = 29
    val minSdkVersion = 26

    val kotlin = "1.3.72"
}

object Deps {
    val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val coreKtx = "androidx.core:core-ktx:1.3.1"
    val appcompat = "androidx.appcompat:appcompat:1.2.0"
    val material = "com.google.android.material:material:1.2.1"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.1"

    val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:2.3.0"
    val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:2.3.0"

    val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-SNAPSHOT"
    val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0-SNAPSHOT"

    val dagger = "com.google.dagger:dagger:2.28.3"
    val daggerCompiler = "com.google.dagger:dagger-compiler:2.28.3"
    val hiltAndroid = "com.google.dagger:hilt-android:2.28.3-alpha"
    val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:2.28.3-alpha"

    val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7"
    val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7"
}
