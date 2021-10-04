/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author luis
 */
public class GuardarDatos {
    public void GuardaraArchivo(String texto) throws IOException{
        try (FileWriter fw = new FileWriter(CargaDatos.archivoAProcesar)) {
            fw.write(texto);
        }
    }
}
