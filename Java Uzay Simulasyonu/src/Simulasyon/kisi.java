/**
*
* @author FERDİ KAYNAR G201210311 FERDİİKAYNAR@GMAIL.COM
* @since 2025-04-27
* <p>
* 2-C
* </p>
*/
package Simulasyon;

/**
 * Kisi sınıfı, her bir yolcunun bilgilerini tutar ve kalan ömrünü günceller.
 */
public class kisi {
    private String isim;
    private int yas;
    private int kalanOmur; // Saat cinsinden kalan ömür
    private String uzayAraciAdi; // Kişinin bulunduğu uzay aracının adı

    /**
     * Kisi nesnesi için yapıcı metot.
     * @param isim Kişinin adı
     * @param yas Kişinin yaşı
     * @param kalanOmur Saat cinsinden kalan ömür
     * @param uzayAraciAdi Kişinin bağlı olduğu uzay aracının adı
     */
    public kisi(String isim, int yas, int kalanOmur, String uzayAraciAdi) {
        this.isim = isim;
        this.yas = yas;
        this.kalanOmur = kalanOmur;
        this.uzayAraciAdi = uzayAraciAdi;
    }

    /**
     * Belirtilen saat kadar kişinin kalan ömrünü azaltır.
     * Eğer kalan ömür 0 veya altına düşerse, kişi ölür kabul edilir.
     * @param saat Azaltılacak saat miktarı
     */
    public boolean hayatGuncelle(int saat) {
        this.kalanOmur -= saat;
        if (this.kalanOmur <= 0) {
            this.kalanOmur = 0;
            return true; // Kişi öldü
        }
        return false; // Kişi hala yaşıyor
    }

    /**
     * Kişinin hala hayatta olup olmadığını kontrol eder.
     * @return Kalan ömür 0'dan büyükse true, değilse false döner.
     */
    public boolean isAlive() {
        return this.kalanOmur > 0;
    }

    // Getter metodları
    public String getIsim() {
        return isim;
    }

    public int getYas() {
        return yas;
    }

    public int getKalanOmur() {
        return kalanOmur;
    }

    public String getUzayAraciAdi() {
        return uzayAraciAdi;
    }

    // Setter metodları (gerekirse)
    public void setUzayAraciAdi(String uzayAraciAdi) {
        this.uzayAraciAdi = uzayAraciAdi;
    }

    public void setKalanOmur(int kalanOmur) {
        this.kalanOmur = kalanOmur;
    }

    @Override
    public String toString() {
        return "Kisi{" +
                "isim='" + isim + '\'' +
                ", yas=" + yas +
                ", kalanOmur=" + kalanOmur +
                ", uzayAraciAdi='" + uzayAraciAdi + '\'' +
                '}';
    }
}

