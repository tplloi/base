package vn.loitp.a.demo.ebookWithRealm.adt

import android.content.Context
import io.realm.RealmResults
import vn.loitp.a.demo.ebookWithRealm.md.Book

class RealmBooksAdapter(
    context: Context,
    realmResults: RealmResults<Book>,
    automaticUpdate: Boolean
) : RealmModelAdapter<Book>(
    context,
    realmResults,
    automaticUpdate
)