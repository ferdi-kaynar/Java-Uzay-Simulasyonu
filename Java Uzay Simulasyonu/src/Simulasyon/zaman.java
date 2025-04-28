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
 * Zaman sınıfı simülasyonda geçen zamanı yönetir.
 * Her döngüde bir saat ilerlemesini sağlar ve gün güncellemesini yapar.
 */
public class zaman {
    private int saat; // Geçerli saat (0-23 arası)
    private int gun;  // Geçerli gün

    /**
     * Varsayılan yapıcı metot: Saat 00:00, gün 1 olarak başlar.
     */
    public zaman() {
        this.saat = 0;
        this.gun = 1;
    }

    /**
     * Zamanı verilen saat kadar ileri alır.
     * Eğer saat 24'ü aşarsa, gün sayısı artar.
     *
     * @param eklenenSaat ilerletilecek saat miktarı
     */
    public void ileriAl(int eklenenSaat) {
        int toplamSaat = this.saat + eklenenSaat;
        this.gun += toplamSaat / 24;
        this.saat = toplamSaat % 24;
    }

    /**
     * Geçerli zamanı formatlanmış string olarak döndürür.
     *
     * @return "Gün X, Saat YY:00" formatında zaman bilgisi
     */
    public String getFormattedTime() {
        return String.format("Gün %d, Saat %02d:00", this.gun, this.saat);
    }

    // Getter metotları
    public int getSaat() {
        return this.saat;
    }

    public int getGun() {
        return this.gun;
    }
}

