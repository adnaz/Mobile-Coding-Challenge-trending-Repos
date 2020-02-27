
# # ANDROID JAVA coding challenge (trending repos)
JAVA ANDROID Project listing the most starred Github repos that were created in the last 30 days.
using MVVM Architecture Pattern, RecyclerView, Retrofit and paging for an infinite scroll .

## Features

As a User I should be able to list the most starred Github repos that were created in the last 30 days.
As a User I should see the results as a list. One repository per row.
As a User I should be able to see for each repo/row the following details :
Repository name
Repository description
Numbers of stars for the repo.
Username and avatar of the owner.
As a User I should be able to keep scrolling and new results should appear (pagination).

#### Android Gradle
```gradle
dependencies {
    //paging
    implementation "androidx.paging:paging-runtime:2.1.1"
    // ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel:2.2.0"
    //Gradle
    implementation 'com.squareup.retrofit2:retrofit:2.7.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.7.2'
    //recyclerview
    implementation 'com.android.support:recyclerview-v7:29.0.0'
    //cardview
    implementation 'com.android.support:cardview-v7:29.0.0'
    //glide
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
}
## Mockups
![alt text](https://github.com/adnaz/Mobile-Coding-Challenge-trending-Repos/blob/master/Capture.PNG?raw=true)
