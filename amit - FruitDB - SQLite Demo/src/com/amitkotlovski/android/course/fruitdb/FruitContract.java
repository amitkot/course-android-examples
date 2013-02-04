package com.amitkotlovski.android.course.fruitdb;

import android.provider.BaseColumns;

public class FruitContract {

    /**
     * Fruit table contract
     */
    public static final class Fruits implements BaseColumns {

        // This class cannot be instantiated
        private Fruits() {}

        /**
         * The table name offered by this provider
         */
        public static final String TABLE_NAME = "fruits";

        /*
         * Column definitions
         */

        /**
         * Column name for the name of the fruit
         * <P>Type: TEXT</P>
         */
        public static final String COLUMN_NAME_NAME = "name";

        /**
         * Column name of the fruit's taste
         * <P>Type: TEXT</P>
         */
        public static final String COLUMN_NAME_TASTE = "taste";

        /**
         * Column name of the fruit's weight
         * <P>Type: REAL</P>
         */
        public static final String COLUMN_NAME_WEIGHT = "weight";
    }
    
}
