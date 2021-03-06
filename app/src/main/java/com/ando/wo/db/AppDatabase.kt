package com.ando.wo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ando.wo.bean.WxArticleTabsEntity
import com.ando.wo.utils.DATABASE_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * Title: AppDatabase
 * <p>
 * Description:
 * </p>
 * @author javakam
 * @date 2020/7/23  15:13
 */
@Database(entities = [WxArticleTabsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wanAndroidDao(): WanAndroidDao

    private class ArticleDatabaseCallback(
        private val scope: CoroutineScope?
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope?.launch {
                    val wanAndroidDao = database.wanAndroidDao()

                    // Delete all content here.
                    //userDao.deleteAll()

                    //延迟一秒
                    //delay(1000)

                    // Add sample users.
//                    var user =
//                        WanAndroidArticleTabsEntity(
//                            uid = null,
//                            nickName = "xiaobao" )
//                    userDao.add(user)

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create the new table
                database.execSQL(
                    "CREATE TABLE t_user_new (uid INTEGER,nick_name TEXT,first_name TEXT, last_name TEXT, PRIMARY KEY(uid))"
                )

                // Copy the data
                database.execSQL(
                    "INSERT INTO t_user_new (uid,first_name,last_name) SELECT uid, first_name, last_name FROM t_user"
                )
                // Remove the old table
                database.execSQL("DROP TABLE t_user")
                // Change the table name to the correct one
                database.execSQL("ALTER TABLE t_user_new RENAME TO t_user")
            }
        }

        fun getDatabase(context: Context, scope: CoroutineScope?): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, DATABASE_NAME
                )
                    //.addMigrations(MIGRATION_1_2)
                    .addCallback(ArticleDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}