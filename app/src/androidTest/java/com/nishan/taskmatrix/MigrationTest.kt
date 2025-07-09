package com.nishan.taskmatrix

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nishan.taskmatrix.data.TaskDatabase
import okio.IOException
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val TEST_DB = "migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        TaskDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun migrate1To2() {
        var db = helper.createDatabase(TEST_DB,1)
        Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java,
            TEST_DB
        ).addAutoMigrationSpec(TaskDatabase.TaskAutoMigration())
            .build()
            .also {
                it.openHelper.writableDatabase.close()
            }
        db = helper.runMigrationsAndValidate(TEST_DB,2,true)
    }
}