import java.util.Scanner;

public class CokgenHesaplayici {

    public static class Nokta {
        double x, y;

        public Nokta(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double mesafe(Nokta diger) {
            double dx = this.x - diger.x;
            double dy = this.y - diger.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    public static class Sekil {
        protected Nokta[] koseler;
        protected double[] kenarlar;
        protected int kosesayisi;

        public Sekil(Nokta[] koseler) {
            this.kosesayisi = koseler.length;
            this.koseler = koseler;
            this.kenarlar = new double[kosesayisi];
            kenarlariHesapla();
        }

        private void kenarlariHesapla() {
            for (int i = 0; i < kosesayisi; i++) {
                Nokta a = koseler[i];
                Nokta b = koseler[(i + 1) % kosesayisi];
                kenarlar[i] = a.mesafe(b);
            }
        }

        public double cevreHesapla() {
            double toplam = 0;
            for (double kenar : kenarlar) {
                toplam += kenar;
            }
            return toplam;
        }

        public double alanHesaplaUcgenIcin() {
            if (kosesayisi != 3) return -1; 

            
            double x1 = koseler[0].x, y1 = koseler[0].y;
            double x2 = koseler[1].x, y2 = koseler[1].y;
            double x3 = koseler[2].x, y3 = koseler[2].y;

            return Math.abs(
                (x1 * (y2 - y3) +
                 x2 * (y3 - y1) +
                 x3 * (y1 - y2)) / 2.0
            );
        }

        public String sekilIsmi() {
            switch (kosesayisi) {
                case 3: return "Üçgen";
                case 4: return "Dörtgen";
                case 5: return "Beşgen";
                case 6: return "Altıgen";
                default: return kosesayisi + " köşeli çokgen";
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Kaç köşe gireceksiniz? ");
        int n = scanner.nextInt();

        if (n < 3) {
            System.out.println("Bir çokgen en az 3 köşe gerektirir.");
            return;
        }

        Nokta[] noktalar = new Nokta[n];
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + ". köşe için koordinatları girin:");
            System.out.print("x: ");
            double x = scanner.nextDouble();
            System.out.print("y: ");
            double y = scanner.nextDouble();
            noktalar[i] = new Nokta(x, y);
        }

        Sekil sekil = new Sekil(noktalar);

        System.out.println("\nŞekil tipi: " + sekil.sekilIsmi());
        System.out.println("Çevre: " + sekil.cevreHesapla());

        if (n == 3) {
            System.out.println("Alan: " + sekil.alanHesaplaUcgenIcin());
        } else {
            System.out.println("Alan sadece üçgenler için hesaplanır.");
        }

        scanner.close();
    }
}
