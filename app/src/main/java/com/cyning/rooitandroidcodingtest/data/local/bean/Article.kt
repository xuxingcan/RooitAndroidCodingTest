package com.cyning.rooitandroidcodingtest.data.local.bean

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Article : RealmObject {
    @PrimaryKey//there is no better choice but url
    var url: String = ""

    var title: String = ""
    var author: String = ""
    var content: String = ""
    var description: String = ""
    var publishedAt: String = ""
    var source: ArticleSource? = null
    var urlToImage: String = ""
}