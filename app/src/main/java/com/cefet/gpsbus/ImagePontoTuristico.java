package com.cefet.gpsbus;

/**
 * Created by diego on 12/03/2018.
 */

public class ImagePontoTuristico {

    public static int getImageById(int id){
        switch (id){
            case 1:
                return R.drawable.museu_imperial;
            case 2:
                return R.drawable.palacio_rio_negro;
            case 3:
                return R.drawable.catedral_sao_pedro_de_alcantara;
            case 4:
                return R.drawable.casa_de_santos_dumont;
            case 5:
                return R.drawable.praca_da_liberdade;
            case 6:
                return R.drawable.parque_natural;
            case 7:
                return R.drawable.museu_da_feb;
            case 8:
                return R.drawable.praca_14_bis;
            case 9:
                return R.drawable.obelisco;
            case 10:
                return R.drawable.palacio_quitandinha;
        }

        return R.drawable.no_image;
    }
}
