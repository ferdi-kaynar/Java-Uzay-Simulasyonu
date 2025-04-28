/**
*
* @author FERDİ KAYNAR G201210311 FERDİİKAYNAR@GMAIL.COM
* @since 2025-04-27
* <p>
* 2-C
* </p>
*/
package Simulasyon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DosyaOkuma sınıfı, dosyalardan verileri okuyarak ilgili nesneleri oluşturur.
 */
public class dosyaokuma {

    /**
     * Kisiler.txt dosyasını okuyarak Kisi nesnelerini oluşturur.
     * Format: isim#yas#kalan_omur#bulundugu_uzay_araci_adi
     *
     * @param dosyaAdi Kisiler.txt dosyasının adı
     * @return Okunan Kisi nesnelerinden oluşan liste
     */
    public static List<kisi> dosyaOkuKisiler(String dosyaAdi) {
        List<kisi> kisiler = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parcalar = line.split("#");
                if (parcalar.length == 4) {
                    kisiler.add(new kisi(
                        parcalar[0],
                        Integer.parseInt(parcalar[1]),
                        Integer.parseInt(parcalar[2]),
                        parcalar[3]
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Kisiler.txt okuma hatası: " + e.getMessage());
            System.err.println("Dosya yolu: " + dosyaAdi);
            e.printStackTrace();
        }
        return kisiler;
    }

    /**
     * Araclar.txt dosyasını okuyarak UzayAraci nesnelerini oluşturur.
     * Format: Uzay_araci_adi#cikis_gezegeni#varis_gezegeni#cikis_tarihi#mesafe_saat_olarak
     *
     * @param dosyaAdi Araclar.txt dosyasının adı
     * @return Okunan UzayAraci nesnelerinden oluşan liste
     */
    public static List<uzayaraci> dosyaOkuAraclar(String dosyaAdi) {
        List<uzayaraci> araclar = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parcalar = line.split("#");
                if (parcalar.length == 5) {
                    araclar.add(new uzayaraci(
                        parcalar[0],
                        parcalar[1],
                        parcalar[2],
                        parcalar[3],
                        Integer.parseInt(parcalar[4])
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Araclar.txt okuma hatası: " + e.getMessage());
            System.err.println("Dosya yolu: " + dosyaAdi);
            e.printStackTrace();
        }
        return araclar;
    }

    /**
     * Gezegenler.txt dosyasını okuyarak Gezegen nesnelerini oluşturur.
     * Format: Gezegen_Adi#Gunun_kac_saat_oldugu#Gezegendeki_tarih
     *
     * @param dosyaAdi Gezegenler.txt dosyasının adı
     * @return Okunan Gezegen nesnelerinden oluşan liste
     */
    public static List<gezegen> dosyaOkuGezegenler(String dosyaAdi) {
        List<gezegen> gezegenler = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parcalar = line.split("#");
                if (parcalar.length == 3) {
                    gezegenler.add(new gezegen(
                        parcalar[0],
                        Integer.parseInt(parcalar[1]),
                        parcalar[2]
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Gezegenler.txt okuma hatası: " + e.getMessage());
            System.err.println("Dosya yolu: " + dosyaAdi);
            e.printStackTrace();
        }
        return gezegenler;
    }
}
