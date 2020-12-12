package DB;

import android.provider.BaseColumns;

public class IncidenciaContract  {
    private IncidenciaContract(){}
    public static class IncidenciaEntry implements BaseColumns {
        public static final String INC_TABLE_NAME ="incidencia";
        public static final String INC_ID = "id";
        public static final String INC_NAME = "title";
        public static final String INC_PRIORITY = "priority";
        public static final String INC_DATE = "date";
        public static final String INC_STATUS = "status";
        public static final String INC_DESCRIPTION = "description";
    }
}
