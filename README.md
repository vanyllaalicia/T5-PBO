# Kalkulator Kalori Makanan

Aplikasi desktop sederhana berbasis Java Swing untuk menghitung dan memantau asupan kalori makanan harian.

## Deskripsi

Kalkulator Kalori Makanan merupakan aplikasi GUI yang membantu pengguna mencatat makanan yang dikonsumsi, menghitung total kalori, serta membandingkan jumlah kalori dengan target harian.

Aplikasi ini dibuat menggunakan bahasa pemrograman Java dengan konsep Pemrograman Berorientasi Objek (PBO) dan library Swing untuk membangun antarmuka grafis.

## Fitur

- **Daftar Makanan:** Memilih makanan dari daftar yang tersedia.
- **Pengaturan Porsi:** Menentukan jumlah porsi makanan.
- **Perhitungan Kalori:** Menghitung kalori berdasarkan jumlah porsi.
- **Daftar Konsumsi:** Menampilkan makanan yang telah ditambahkan dalam tabel.
- **Hapus Makanan:** Menghapus makanan yang dipilih dari tabel.
- **Reset Daftar:** Mengosongkan seluruh daftar makanan.
- **Ringkasan Kalori:** Menampilkan total kalori dan jumlah makanan.
- **Target Kalori Harian:** Membandingkan total kalori dengan target harian yang ditentukan.

## Teknologi

- Java Development Kit (JDK) 25
- Java Swing
- Java AWT
- IntelliJ IDEA

## Struktur File

```text
TugasGUI/
├── KalkulatorKaloriMakanan.java
├── bin/
│   └── KalkulatorKaloriMakanan.class
└── README.md
```

File `.class` merupakan hasil kompilasi dari source code Java dan dapat disimpan di dalam folder `bin`.

## Cara Menjalankan

1. Pastikan JDK sudah terinstal.
2. Buka terminal pada direktori proyek.
3. Kompilasi source code ke folder `bin`:

   ```bash
   javac -d bin KalkulatorKaloriMakanan.java
   ```

4. Jalankan aplikasi:

   ```bash
   java -cp bin KalkulatorKaloriMakanan
   ```

## Cara Penggunaan

1. Jalankan aplikasi Kalkulator Kalori Makanan.
2. Pilih makanan yang ingin dicatat.
3. Tentukan jumlah porsi.
4. Klik **Tambah ke Daftar**.
5. Lihat total kalori pada bagian ringkasan.
6. Sesuaikan target kalori harian sesuai kebutuhan.
7. Gunakan tombol **Hapus Terpilih** untuk menghapus makanan tertentu atau **Kosongkan Daftar** untuk menghapus seluruh data.

## Catatan

- Nilai kalori makanan merupakan estimasi dan dapat berbeda berdasarkan bahan, ukuran porsi, serta metode pengolahan.
- Data makanan hanya tersimpan selama aplikasi berjalan dan belum menggunakan database.

---

**Project:** Kalkulator Kalori Makanan  
**Programming Language:** Java  
**Application Type:** Desktop GUI  
**Course:** Pemrograman Berorientasi Objek (PBO)