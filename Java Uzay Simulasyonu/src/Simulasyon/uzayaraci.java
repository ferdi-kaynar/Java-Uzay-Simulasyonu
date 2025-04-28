/**
*
* @author FERDİ KAYNAR G201210311 FERDİİKAYNAR@GMAIL.COM
* @since 2025-04-27
* <p>
* 2-C
* </p>
*/
package Simulasyon;

import java.util.ArrayList;
import java.util.List;

/**
 * UzayAraci sınıfı, aracın seyahat bilgilerini ve içindeki yolcuları yönetir.
 */
public class uzayaraci {
    private String ad;
    private String cikisGezegeni;
    private String varisGezegeni;
    private String cikisTarihi;  // Örneğin "3.11.2022"
    private int mesafe;          // Seyahat süresi, saat cinsinden
    private String durum;        // "Gezegende", "Yolda", "İMHA"
    private List<kisi> yolcular;
    private int kalanMesafe;

    /**
     * UzayAraci nesnesi için yapıcı metot.
     * @param ad Aracın adı
     * @param cikisGezegeni Çıkış gezegeninin adı
     * @param varisGezegeni Varış gezegeninin adı
     * @param cikisTarihi Çıkış tarihi
     * @param mesafe Seyahat süresi (saat olarak)
     */
    public uzayaraci(String ad, String cikisGezegeni, String varisGezegeni, String cikisTarihi, int mesafe) {
        this.ad = ad;
        this.cikisGezegeni = cikisGezegeni;
        this.varisGezegeni = varisGezegeni;
        this.cikisTarihi = cikisTarihi;
        this.mesafe = mesafe;
        this.durum = "Beklemede";
        this.yolcular = new ArrayList<>();
        this.kalanMesafe = mesafe;
    }

    // Getter metodları
    public String getAd() {
        return ad;
    }

    public String getCikisGezegeni() {
        return cikisGezegeni;
    }

    public String getVarisGezegeni() {
        return varisGezegeni;
    }

    public String getCikisTarihi() {
        return cikisTarihi;
    }

    public int getMesafe() {
        return mesafe;
    }

    public String getDurum() {
        return durum;
    }

    public List<kisi> getYolcular() {
        return yolcular;
    }

    public int getKalanMesafe() {
        return kalanMesafe;
    }

    // Setter metodları
    public void setDurum(String durum) {
        this.durum = durum;
    }

    /**
     * Araca yeni bir yolcu ekler.
     * @param k Eklenmek istenen kişi nesnesi
     */
    public void addYolcu(kisi k) {
        yolcular.add(k);
    }

    public void setKalanMesafe(int kalanMesafe) {
        this.kalanMesafe = kalanMesafe;
    }

    /**
     * Aracın seyahate başlamasını sağlar.
     * Durum "Yolda" olarak ayarlanır ve kalan saat mesafeye eşitlenir.
     */
    public void baslatSeyahat() {
        if (durum.equals("Gezegende")) {
            durum = "Yolda";
        }
    }

    /**
     * Aracın içerisindeki tüm yolcuların ölüp ölmediğini kontrol eder.
     * @return Tüm yolcular ölmüşse true, değilse false.
     */
    public boolean tumYolcularOlduMu() {
        for (kisi k : yolcular) {
            if (k.isAlive()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Eğer tüm yolcular ölmüşse, aracın durumunu "İMHA" olarak günceller.
     */
    public void imhaKontrol() {
        if (tumYolcularOlduMu()) {
            durum = "İMHA";
        }
    }

    @Override
    public String toString() {
        return "UzayAraci{" +
                "ad='" + ad + '\'' +
                ", cikisGezegeni='" + cikisGezegeni + '\'' +
                ", varisGezegeni='" + varisGezegeni + '\'' +
                ", cikisTarihi='" + cikisTarihi + '\'' +
                ", mesafe=" + mesafe +
                ", durum='" + durum + '\'' +
                ", yolcuSayisi=" + yolcular.size() +
                '}';
    }
}

