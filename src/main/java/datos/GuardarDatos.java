/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Esta clase me permite guardar los datos realizados a mi archivo de entrada, con la finalidad de no perder los datos almacenados
 * @author luis
 */
public class GuardarDatos {

    /**
     * Este metodo me permite guardar los datos realizados a mi archivo de entrada, con la finalidad de no perder los datos almacenado a travez del parametro del texto ingresado
     * @param texto
     * @throws IOException
     */
    public void GuardaraArchivo(String texto) throws IOException{
        try (FileWriter fw = new FileWriter(CargaDatos.archivoAProcesar)) {
            fw.write(texto);
        }
    }
}
