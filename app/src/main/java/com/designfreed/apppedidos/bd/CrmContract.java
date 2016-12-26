package com.designfreed.apppedidos.bd;

import android.provider.BaseColumns;

public final class CrmContract {
    private CrmContract() {

    }

    public static final class HojaRuta implements BaseColumns {
        public static final String TABLE_NAME = "hojas_ruta";

        public static final String COLUMN_ID_HOJA_RUTA = "id_hoja_ruta";
        public static final String COLUMN_FECHA = "fecha";
        public static final String COLUMN_ID_CHOFER = "id_chofer";
        public static final String COLUMN_ESTADO = "estado";

        public static final Integer ESTADO_CERRADA = 0;
        public static final Integer ESTADO_ABIERTA = 1;
    }

    public static final class MovimientoEnc implements BaseColumns {
        public static final String TABLE_NAME = "movimientos_enc";

        public static final String COLUMN_ID_MOVIMIENTO_ENC = "id_movimiento_enc";
        public static final String COLUMN_FECHA = "fecha";
        public static final String COLUM_ID_CLIENTE = "id_cliente";
        public static final String COLUM_ID_CONDICION_VENTA = "id_condicion_venta";
        public static final String COLUM_ID_TIPO_MOVIMIENTO = "id_tipo_movimiento";
        public static final String COLUM_ID_ESTADO_MOVIMIENTO = "id_estado_movimiento";
        public static final String COLUM_VISITO = "visito";
        public static final String COLUM_VENDIO = "vendio";
        public static final String COLUM_ID_MOTIVO = "id_motivo";
        public static final String COLUM_ID_HOJA_RUTA = "id_hoja_ruta";
        public static final String COLUM_SYNC = "sync";

        public static final Integer TIPO_MOVIMIENTO_PRERUTEO = 1;
        public static final Integer TIPO_MOVIMIENTO_PEDIDO = 2;
        public static final Integer TIPO_MOVIMIENTO_VOLEO = 3;

        public static final Integer ESTADO_MOVIMIENTO_ENTREGADO = 3;
        public static final Integer ESTADO_MOVIMIENTO_NO_ENTREGADO = 4;
        public static final Integer ESTADO_MOVIMIENTO_RECHAZADO = 5;
        public static final Integer ESTADO_MOVIMIENTO_COMUNICADO = 6;

        public static final Integer NO_VISITO = 0;
        public static final Integer VISITO = 1;

        public static final Integer NO_VENDIO = 0;
        public static final Integer VENDIO = 1;

        public static final Integer NO_SYNC = 0;
        public static final Integer SYNC = 1;
    }

    public static final class MovimientoDet implements BaseColumns {
        public static final String TABLE_NAME = "movimientos_det";

        public static final String COLUMN_ID_MOVIMIENTO_DET = "id_movimiento_enc";
        public static final String COLUMN_ID_MOVIMIENTO_ENC = "id_movimiento_det";
        public static final String COLUMN_ID_ENVASE = "id_envase";
        public static final String COLUMN_CANTIDAD = "cantidad";
        public static final String COLUMN_MONTO = "monto";
    }
}
