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
import java.util.Scanner;
import java.io.IOException;

/**
 * Simülasyon sınıfı, kişileri, uzay araçlarını ve gezegenleri yönetir,
 * simülasyon döngüsünü kontrol eder ve her saatlik iterasyonda durumu günceller.
 */
public class simulasyon {
    private List<kisi> kisiler;
    private List<uzayaraci> araclar;
    private List<gezegen> gezegenler;
    private boolean devamEdiyor;

    /**
     * Simülasyon nesnesi için yapıcı metot.
     * @param kisiler Kişi nesnelerinin listesi
     * @param araclar Uzay aracının listesi
     * @param gezegenler Gezegen nesnelerinin listesi
     */
    public simulasyon(List<kisi> kisiler, List<uzayaraci> araclar, List<gezegen> gezegenler) {
        this.kisiler = kisiler;
        this.araclar = araclar;
        this.gezegenler = gezegenler;
        this.devamEdiyor = true;

        // Kişileri araçlara yerleştir ve başlangıç nüfuslarını ayarla
        for (kisi k : kisiler) {
            for (uzayaraci a : araclar) {
                if (a.getAd().equals(k.getUzayAraciAdi())) {
                    a.addYolcu(k);
                    // Başlangıç gezegenine nüfusu ekle
                    for (gezegen g : gezegenler) {
                        if (g.getAd().equals(a.getCikisGezegeni())) {
                            g.nufusEkle(1);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * Simülasyonu başlatır. Her saatlik döngüde tüm nesnelerin durumunu günceller.
     */
    public void baslat() {
        Scanner scanner = new Scanner(System.in);
        while (devamEdiyor) {
            try {
                // Ekranı güncelle
                ekraniGuncelle();
                
                // Simülasyonu bir saat ilerlet
                saatIlerlet();

                // 1 saniye bekle
                Thread.sleep(1000);
                
                // Kullanıcı girişini kontrol et
                if (System.in.available() > 0) {
                    char input = scanner.next().charAt(0);
                    if (input == 'q' || input == 'Q') {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (IOException e) {
                // Giriş kontrolü hatası, devam et
            }
        }
        scanner.close();
    }

    private void ekraniGuncelle() {
        // Ekranı temizle
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Temizleme başarısız olursa 50 satır boşluk ekle
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }

        // Simülasyon durumunu yazdır
        System.out.println("Simülasyon Durumu:");
        System.out.println();

        // Gezegenleri yazdır
        System.out.println("Gezegenler:");
        System.out.println();

        // Gezegen adları
        for (gezegen g : gezegenler) {
            System.out.printf("%-20s", "   " + g.getAd() + "   ");
        }
        System.out.println();

        // Gezegen tarihleri
        for (gezegen g : gezegenler) {
            System.out.printf("%-20s", g.getTarih());
        }
        System.out.println();

        // Gezegen nüfusları
        for (gezegen g : gezegenler) {
            System.out.printf("%-20d", g.getNufus());
        }
        System.out.println("\n");

        // Uzay Araçlarını yazdır
        System.out.println("Uzay Araçları:");
        System.out.println();
        
        // Başlık satırı
        System.out.printf("%-12s %-10s %-12s %-12s %-20s %-20s\n",
            "Araç Adı", "Durum", "Çıkış", "Varış", "Hedefe Kalan Saat", "Hedefe Var. Tarih");
        System.out.println();
        
        // Araç bilgileri
        for (uzayaraci a : araclar) {
            String hedefeKalanSaat = a.getDurum().equals("İMHA") ? "--" : String.valueOf(a.getKalanMesafe());
            String hedefeVarisTarih = a.getDurum().equals("İMHA") ? "--" : hesaplaVarisTarihi(a);
            
            System.out.printf("%-12s %-10s %-12s %-12s %-20s %-20s\n",
                a.getAd(),
                a.getDurum(),
                a.getCikisGezegeni(),
                a.getVarisGezegeni(),
                hedefeKalanSaat,
                hedefeVarisTarih);
        }

        // Çıkış talimatını göster
        System.out.println("\nSimülasyonu durdurmak için 'q' tuşuna basın...");
    }

    private void saatIlerlet() {
        // Gezegenlerde zamanı ilerlet
        for (gezegen g : gezegenler) {
            g.zamanIlerlet();
        }

        // Araçları kontrol et ve güncelle
        for (uzayaraci a : araclar) {
            // Yolcuların ömürlerini azalt
            for (kisi k : a.getYolcular()) {
                if (k.isAlive()) {
                    k.hayatGuncelle(1);
                }
            }

            // Tüm yolcular öldüyse aracı imha et
            if (a.tumYolcularOlduMu() && !a.getDurum().equals("İMHA")) {
                a.imhaKontrol();
                continue;
            }

            // Aracın durumunu kontrol et
            if (a.getDurum().equals("Beklemede")) {
                // Çıkış zamanı geldiyse harekete geç
                for (gezegen g : gezegenler) {
                    if (g.getAd().equals(a.getCikisGezegeni())) {
                        // Tarih kontrolü
                        if (g.getTarih().equals(a.getCikisTarihi())) {
                            a.setDurum("Yolda");
                            // Çıkış gezegeninden nüfusu düş
                            g.nufusCikar(a.getYolcular().size());
                            break;
                        }
                    }
                }
            } else if (a.getDurum().equals("Yolda")) {
                // Mesafeyi azalt
                a.setKalanMesafe(a.getKalanMesafe() - 1);
                if (a.getKalanMesafe() <= 0) {
                    a.setDurum("Vardı");
                    // Varış gezegenine nüfusu ekle
                    for (gezegen g : gezegenler) {
                        if (g.getAd().equals(a.getVarisGezegeni())) {
                            g.nufusEkle(a.getYolcular().size());
                            break;
                        }
                    }
                }
            }
        }

        // Simülasyonun bitip bitmediğini kontrol et
        boolean tumAraclarVardi = true;
        for (uzayaraci a : araclar) {
            if (!a.getDurum().equals("Vardı") && !a.getDurum().equals("İMHA")) {
                tumAraclarVardi = false;
                break;
            }
        }
        if (tumAraclarVardi) {
            devamEdiyor = false;
        }
    }

    private String hesaplaVarisTarihi(uzayaraci arac) {
        if (arac.getDurum().equals("İMHA") || arac.getDurum().equals("Vardı")) {
            return "--";
        }

        // Varış gezegenini bul
        for (gezegen g : gezegenler) {
            if (g.getAd().equals(arac.getVarisGezegeni())) {
                // Kalan yolculuk süresini varış gezegeninin gün uzunluğuna göre hesapla
                double gunSayisi = (double) arac.getKalanMesafe() / g.getGunSaat();
                int tamGunSayisi = (int) Math.ceil(gunSayisi);

                // Mevcut tarihi parçala
                String[] tarihParcalari = g.getTarih().split("\\.");
                int gun = Integer.parseInt(tarihParcalari[0]);
                int ay = Integer.parseInt(tarihParcalari[1]);
                int yil = Integer.parseInt(tarihParcalari[2]);

                // Tarihi güncelle
                gun += tamGunSayisi;
                while (gun > 30) {
                    gun -= 30;
                    ay++;
                    if (ay > 12) {
                        ay = 1;
                        yil++;
                    }
                }

                return String.format("%02d.%02d.%d", gun, ay, yil);
            }
        }
        return "--";
    }

    public static void main(String[] args) {
        // Dosyalardan verileri oku
        List<kisi> kisiler = dosyaokuma.dosyaOkuKisiler("src/imulasyon/kisiler.txt");
        List<uzayaraci> araclar = dosyaokuma.dosyaOkuAraclar("src/Simulasyon/araclar.txt");
        List<gezegen> gezegenler = dosyaokuma.dosyaOkuGezegenler("src/Simulasyon/gezegenler.txt");

        // Simülasyonu başlat
        simulasyon sim = new simulasyon(kisiler, araclar, gezegenler);
        sim.baslat();
    }
}
