package darwinvtomy.open.com.infosysassignment.db;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import darwinvtomy.open.com.infosysassignment.model.Canada;

@Dao
public interface CanadaDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from canada_table")
    LiveData<List<Canada.RowsBean>> getAlphabetizedWords();

    // We do not need a conflict strategy, because the title is our primary key, and you cannot
    // add two items with the same primary key to the database. If the table has more than one
    // column, you can use @Insert(onConflict = OnConflictStrategy.REPLACE) to update a row.
    @Insert
    void insert(Canada.RowsBean news);

    @Query("DELETE FROM canada_table")
    void deleteAll();

}
