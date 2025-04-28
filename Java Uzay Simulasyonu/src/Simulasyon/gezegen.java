/**
*
* @author FERDİ KAYNAR G201210311 FERDİİKAYNAR@GMAIL.COM
* @since 2025-04-27
* <p>
* 2-C
* </p>
*/
package Simulasyon;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Calendar;

/**
 * Gezegen sınıfı, her gezegenin zaman dilimi ve tarih bilgilerini yönetir.
 */
public class gezegen {
    private String ad;        // Gezegenin adı
    private int gunSaat;      // Bir günün kaç saat sürdüğü (örneğin 24)
    private String tarih;     // Gezegenin başlangıç tarihi (örn: "5.10.2020")
    private int saat;         // Gezegenin mevcut saati
    private int nufus;         // Gezegenin nüfus sayısı

    /**
     * Yapıcı metot, gezegenin adı, bir günün saat sayısı ve başlangıç tarihini alır.
     * @param ad Gezegenin adı
     * @param gunSaat Bir günün kaç saat sürdüğü
     * @param tarih Gezegenin başlangıç tarihi
     */
    public gezegen(String ad, int gunSaat, String tarih) {
        this.ad = ad;
        this.gunSaat = gunSaat;
        this.tarih = tarih;
        this.saat = 0; // Her gezegen 00:00'dan başlar.
        this.nufus = 0;
    }

    /**
     * Gezegenin saatini, simülasyonda geçen süreye göre günceller.
     * Eğer toplam saat, bir günü aşarsa, gün sayısı artar ve tarih güncellenebilir.
     *
     * @param saat Simülasyonda geçen saat miktarı
     */
    public void saatGuncelle(int saat) {
        this.saat += saat;
        while (this.saat >= gunSaat) {
            this.saat -= gunSaat;
            // Tarihi bir gün ileri al
            String[] tarihParcalari = tarih.split("\\.");
            int gun = Integer.parseInt(tarihParcalari[0]);
            int ay = Integer.parseInt(tarihParcalari[1]);
            int yil = Integer.parseInt(tarihParcalari[2]);
            
            gun++;
            if (gun > 30) {
                gun = 1;
                ay++;
                if (ay > 12) {
                    ay = 1;
                    yil++;
                }
            }
            
            this.tarih = String.format("%02d.%02d.%d", gun, ay, yil);
        }
    }

    public void nufusEkle(int sayi) {
        this.nufus += sayi;
    }

    public void nufusCikar(int sayi) {
        this.nufus -= sayi;
        if (this.nufus < 0) {
            this.nufus = 0;
        }
    }

    /**
     * Gezegenin güncel zaman bilgisini formatlanmış şekilde döndürür.
     * @return Gezegen adı, tarih ve saat bilgisini içeren string
     */
    @Override
    public String toString() {
        return "Gezegen{" +
               "ad='" + ad + '\'' +
               ", gunSaat=" + gunSaat +
               ", tarih='" + tarih + '\'' +
               ", saat=" + saat +
               ", nufus=" + nufus +
               '}';
    }

    // Getter metodları
    public String getAd() {
        return ad;
    }

    public int getGunSaat() {
        return gunSaat;
    }

    public String getTarih() {
        return tarih;
    }

    public int getSaat() {
        return saat;
    }

    public int getNufus() {
        return nufus;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public void setSaat(int saat) {
        this.saat = saat;
    }

    public void setNufus(int nufus) {
        this.nufus = nufus;
    }

    public void zamanIlerlet() {
        saat++;
        if (saat >= gunSaat) {
            saat = 0;
            // Tarihi bir gün ilerlet
            String[] tarihParcalari = tarih.split("\\.");
            int gun = Integer.parseInt(tarihParcalari[0]);
            int ay = Integer.parseInt(tarihParcalari[1]);
            int yil = Integer.parseInt(tarihParcalari[2]);

            gun++;
            if (gun > 30) {
                gun = 1;
                ay++;
                if (ay > 12) {
                    ay = 1;
                    yil++;
                }
            }
            tarih = String.format("%02d.%02d.%d", gun, ay, yil);
        }
    }
}
