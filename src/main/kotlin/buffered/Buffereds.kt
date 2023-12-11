package buffered

import utils.MisCharsets
import utils.ResultsFile
import java.io.*
import java.util.regex.Pattern

/**
 * Lee el archivo y escribe el nuevo archivo
 */
fun leerArchivoBuffer(miTxt: String, filenameOut: String, cod: String, delimiter: String): ResultsFile {

    val resultsFile = ResultsFile(0, filenameOut, 0, 0, 0, true)

    val outWriter = createWrite(resultsFile.fileName ?: "", cod)

    try {
        val bufferedReader =
            BufferedReader(InputStreamReader(FileInputStream(miTxt), MisCharsets.mischarsets.getValue(cod)))

        var line: String? = bufferedReader.readLine()
        while (line != null) {

            resultsFile.lineFile++
            val separator = Pattern.quote(delimiter)
            val parts = line.split(separator.toRegex())

            if (line.contains(delimiter)) resultsFile.lineDelimeter++

            if (line.hashCode() == 0) resultsFile.lineVacia++

            if (parts[0].hashCode() != 0) {

                outWriter?.appendLine(parts[0])
            } else resultsFile.linesMod++

            line = bufferedReader.readLine()
        }

        resultsFile.enabled = false

        bufferedReader.close()
        outWriter?.flush()
        outWriter?.close()
    } catch (f: FileNotFoundException) {
        f.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        println("Fin de Lectura")

    }

    return resultsFile
}


/**
 * crea un buffered Writer para escribirn en el archivo que crea
 */
fun createWrite(file: String, codificacion: String): PrintWriter? {
    try {
        val bw =
            BufferedWriter(OutputStreamWriter(FileOutputStream(file), MisCharsets.mischarsets.getValue(codificacion)))
        return PrintWriter(bw)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}