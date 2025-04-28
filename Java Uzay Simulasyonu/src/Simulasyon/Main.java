/**
*
* @author FERDİ KAYNAR G201210311 FERDİİKAYNAR@GMAIL.COM
* @since 2025-04-27
* <p>
* 2-C
* </p>
*/
package Simulasyon;
import java.util.List;

public class Main {
	public static void main(String[] args) {
        // Dosya yollarını JAR dosyasının bulunduğu konuma göre ayarla
        String kisilerDosya = "kisiler.txt";
        String araclarDosya = "araclar.txt";
        String gezegenlerDosya = "gezegenler.txt";

        // DosyaOkuma sınıfı ile verileri oku
        List<kisi> kisiler = dosyaokuma.dosyaOkuKisiler(kisilerDosya);
        List<uzayaraci> araclar = dosyaokuma.dosyaOkuAraclar(araclarDosya);
        List<gezegen> gezegenler = dosyaokuma.dosyaOkuGezegenler(gezegenlerDosya);

        // Simülasyonu başlat
        simulasyon sim = new simulasyon(kisiler, araclar, gezegenler);
        sim.baslat();
    }
}
