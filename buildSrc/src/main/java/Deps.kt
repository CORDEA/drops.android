object Versions {
    val compileSdkVersion = 29
    val minSdkVersion = 26

    val kotlin = "1.3.72"
    val navigation = "2.3.0"
}

object Deps {
    val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val coreKtx = "androidx.core:core-ktx:1.3.1"
    val appcompat = "androidx.appcompat:appcompat:1.2.0"
    val material = "com.google.android.material:material:1.2.1"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.1"
    val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
    val coreTesting = "androidx.arch.core:core-testing:2.1.0"

    val navigationSafeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-SNAPSHOT"
    val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0-SNAPSHOT"

    val dagger = "com.google.dagger:dagger:2.28.3"
    val daggerCompiler = "com.google.dagger:dagger-compiler:2.28.3"
    val hiltAndroid = "com.google.dagger:hilt-android:2.28.3-alpha"
    val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:2.28.3-alpha"

    val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1"
    val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.1"
    val kotlinxCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.1"

    val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    val converterMoshi = "com.squareup.retrofit2:converter-moshi:2.9.0"

    val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.9.3"
    val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:1.9.3"

    val groupie = "com.github.lisawray.groupie:groupie:2.8.1"
    val groupieViewbinding = "com.github.lisawray.groupie:groupie-viewbinding:2.8.1"

    val coil = "io.coil-kt:coil:1.0.0"

    val timber = "com.jakewharton.timber:timber:4.7.1"

    val junit = "junit:junit:4.12"
    val mockk = "io.mockk:mockk:1.10.2"
    val striktCore = "io.strikt:strikt-core:0.28.0"
    val striktMockk = "io.strikt:strikt-mockk:0.28.0"
}
