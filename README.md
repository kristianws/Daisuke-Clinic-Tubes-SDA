## Clinic Management System

Sistem managemen clinic berbasis Java ini memungkinkan pengguna untuk:

- Mengelola data pasien dan dokter
- Mengatur jadwal appointment dokter
- Menampilkan informasi dengan antarmuka menu berbasis teks

Anggota Kelompok:
* Kristian Pandu Wijaya Subijakto (L0124060)
* Muhammad Rafi Raihan Sonjaya (L0124065)
* Nabil Rizki Wardana (L0124066)
* Saskya Aliya Azizah (L0124076)
* Jesayas Hutasoit (L0124101)

Kontribusi Anggota:
* Nabil : Struktur data, revisi program
* Saskya : struktur data, developing program, rivisi program 
* Kristian : rivisi program, uji fitur program 
* Jesayas : uji fitur program, pembuatan read me
* Rafi : uji fitur program, pembuatan video demonstras
------

## Struktur Direktori Program
- `Main.java`
    - `Doctor.java`
    - `Admin.java`
    - `Patient.java`      
------

## Cara Penggunaan Program

### Memulai Program

1.  **Persiapan**:
    * Pastikan Anda telah menginstal Java Development Kit (JDK) 8.
    * Buka terminal atau VsCode

2.  **Kompilasi Semua File**:
    Kompilasi semua file Java yang ada di direktori tersebut kedalam satu folder.

3.  **Jalankan Program**:
    Setelah kompilasi berhasil, jalankan program utama pada VsCode:
    ```bash
    Main.java
    ```

### Alur Program dan Opsi Menu

Setelah program berjalan, Anda akan disambut dengan halaman login.

**1. Halaman Login**

Halaman ini adalah titik utama program. Anda akan diminta untuk memilih ingin melakukan apa:

![image](https://github.com/user-attachments/assets/4d7ff9f3-15dd-4f0e-b55c-785462c9b2e1)


* **[1] Login**: Memungkinkan Anda untuk masuk sebagai Pasien, Dokter maupun Admin.
* **[2] Register**: Memungkinkan Anda untuk masuk membuat akun baru sebagai Dokter dan Admin.
* **[3] Guest Account**: Memungkinkan Anda untuk masuk pasien yang belum terdaftar.
* **[0] Exit**: Memungkinkan Anda untuk menghetikan program.

**2. fitur Login [1]**

Jika Anda memilih opsi `1` (Login) pada halaman utama, Anda akan diminta untuk memasukkan ID (sesuai ingin login sebagai apa), jika anda login sebagai dokter atau admin anda akan disuruh mengisi nama dan pasword.

![image](https://github.com/user-attachments/assets/8b2ed219-3002-49ba-9191-08cefaeb148c)

![image](https://github.com/user-attachments/assets/38a6f271-f66d-4291-bff6-45d0417fc0b5)

Tetapi jika anda ingin login sebagai pasien anda hanya harus mengisi id dan nama.

![image](https://github.com/user-attachments/assets/036328a2-6090-4baf-bd35-ca85751efadc)

**2.1. Login sebagai dokter**

Setelah berhasil login sebagai dokter, program akan memastikan anda sudah login:

![image](https://github.com/user-attachments/assets/b5c70da2-af2c-4e54-b440-cb6260e7b8a1)

Setelah menekan tombol enter, anda akan diperlihatkan menu-menu berikut:

![image](https://github.com/user-attachments/assets/6c319783-05e7-43c9-8be6-aaf1865ef67c)

* **[1] Patient Management**:
  
  ![image](https://github.com/user-attachments/assets/3e61df37-3eb0-4f55-adf8-c692c5d95d09)

    * [1] Memungkinkan Anda membuat mencari pasien dari namanya.
      
      Masukan nama pasien yang ingin dicari.

      ![image](https://github.com/user-attachments/assets/1659eeb4-4d32-4911-82bd-d2824b80201f)

      Setelah memasukan nama akan muncul data pasien yang dicari.
      
      ![image](https://github.com/user-attachments/assets/3845d50d-4273-47b5-9fc0-0b3276ecf086)

    * [2] Menunjukan semua pasien.
      
      ![image](https://github.com/user-attachments/assets/871edb48-3023-494c-a105-c08027d62408)

    * [3] Mencari pasien melaliu id.
      
      Setelah memilih opsi ini anda akan diminta memasukan nomor id pasien untuk mencari pasien tersebut.

      ![image](https://github.com/user-attachments/assets/8819941d-f4da-4b47-8fac-44e6661f2d82)

      Setelah memasukan id pasien, maka program akan menampilkan data pasien.
      
      ![image](https://github.com/user-attachments/assets/fd18c2ea-a7dc-497e-8a6f-2111af70fe52)

    * [4] Mendisplay pasien yang diurutkan melalui BST Inorder.
      
      ![image](https://github.com/user-attachments/assets/f5d885ab-825f-49cb-b797-9378e7a3a38a)

    * [5] Mendisplay pasien yang diurutkan melalui BST Preorder.
      
      ![image](https://github.com/user-attachments/assets/515de0db-2d1d-451e-9418-63d1b564ccf5)

    * [6] Mendisplay pasien yang diurutkan melalui BST Postorder.
      
      ![image](https://github.com/user-attachments/assets/1ad70a54-22de-46fd-a9c7-59bb2f37ce84)

    * [0] Kembali ke menu dokter.

* **[2] Doctor Management**:

    ![image](https://github.com/user-attachments/assets/7ae77485-0c48-45d7-85e0-5d8822051c8e)

    * [1] Dokter Logout.
 
      ![image](https://github.com/user-attachments/assets/b6ec921f-bbf9-45b7-8d1c-7b8272841833)

    * [2] Melihat dokter yang terakhir kali login.

      ![image](https://github.com/user-attachments/assets/c3ddab8d-be89-48fa-b0e4-b4c47fa96818)

    * [3] Melihat semua dokter yang sudah login.
 
      ![image](https://github.com/user-attachments/assets/43f55bf0-cd96-4469-b97c-6e96e9c7cb84)

    * [4] Mendisplay semua dokter yang ada di klinik daisuke.
 
      ![image](https://github.com/user-attachments/assets/53af34a3-e288-47d8-8a05-856aa64ce989)

    * [5] Mencari dokter melalui nama.
 
      Setelah memilih opsi ini anda akan diminta untuk memasuk nama dokter (nama lengkap).
      
      ![image](https://github.com/user-attachments/assets/4682eec6-399e-4ee0-bf40-7f0ca16629a5)

      Setelah itu akan memunculkan data dokter.
      
      ![image](https://github.com/user-attachments/assets/70ece636-3c76-41c9-97b5-d2830d52ae4e)

    * [6] Mencari dokter melaliu id.
 
      Setelah memilih opsi ini anda akan diminta untuk memasuk id dokter (hanya nomor).
      
      ![image](https://github.com/user-attachments/assets/229c5362-01c9-4f2b-ba8c-f105b3e1f272)

      Setelah itu akan menampilkan data dokter
      
      ![image](https://github.com/user-attachments/assets/04c6e1b8-88c6-4554-85b1-229d9dda330f)

    * [0] Kembali ke menu dokter.

* **[3] Appointment Management**:
  
  ![image](https://github.com/user-attachments/assets/c0335679-3c26-40d3-b60c-6281f43b3d62)
    
    * [1] Membuat janji temu.
 
      Setelah memilih menu ini anda akan diminta memasukkan Speciality, id pasien, id dokter, complaint, tanggal, dan waktu temu.
      
      ![image](https://github.com/user-attachments/assets/5d5e21f0-2017-45f9-a6a8-100b383f5210)

      Jika tidak ada dokter yang bisa melakukan janji temu program akan membuat peringatan.

      ![image](https://github.com/user-attachments/assets/97e7c563-4912-4b12-9566-6f51b23eea6e)

      Jika berhasil didaftarkan maka program akan memunculkan.

      ![image](https://github.com/user-attachments/assets/58a71eda-c1da-4e03-a503-12eec123a926)

    
    * [2] Memproses janji temu.
 
      Setelah memilih opsi ini anda akan diminta untuk memasukan Speciality yang ingin diobati, setelah itu akan diberikan Appointment yang mendaftar lebih awal (dari tanggalnya).

      ![image](https://github.com/user-attachments/assets/90dce1c1-a39f-4c88-94f0-d9d5dd72c6c8)

      Setelah melihat data dan menekan enter maka anda akan diminta mengisi data perawatan pasien tersebut.
       
      ![image](https://github.com/user-attachments/assets/2a9800f1-a8ed-4371-9ad7-16507f6380d4)

      Setelah selesai mengisi data perawatan maka program akan mencetak Medical Record Patient.

      ![image](https://github.com/user-attachments/assets/c2939e47-1d17-4c96-8112-542be2fb0923)

    * [3] Mendisplay semua janji temu yang akan dilakukan.
 
      Setelah memilih opsi ini anda akan diminta untuk memasukkan Speciality Appointment, setelah itu program akan menampilkan semua Speciality Appointment yang belum dilakukan.

      ![image](https://github.com/user-attachments/assets/c73c73b0-4ab6-49df-82e4-ef02d11572ea)
 
    * [0] kembali ke menu dokter.
      
* **[4] Medical Record Management**:
  
    * [1] Mencari Medical Record pasien melalui nama.

      Setelah memilih menu ini anda akan diminta memasukan nama pasien.
      
      ![image](https://github.com/user-attachments/assets/e378bad7-407e-4a5d-baa8-329f7ca3d45c)

      Setelah itu program akan menampilkan data Medicar Record dari pasien tersebut.

      ![image](https://github.com/user-attachments/assets/8b385ed8-09e7-499b-8737-0a1cdd7167c0)

    * [2] Mendisplay semua Medical Record.

      ![image](https://github.com/user-attachments/assets/1a5be561-4917-4dcb-bec5-e2144c61d6d9)

    * [0] Kembali ke menu dokter.
  
* **[5] Practice Schedule**:
  
    * [1] Mencari jadwal dokter dengan nama.
 
      Setelah memilih opsi ini anda diminta memasukan nama dokter.

      ![image](https://github.com/user-attachments/assets/40293621-ab49-4045-b2b7-c7b78e5d5a14)

      Lalu program akan memunculkan jadwal dokter tersebut.

      ![image](https://github.com/user-attachments/assets/e3554c5d-c1e3-4376-b79f-d84dd814d9bd)

    * [2] Mencari jadwal dokter dengan id.
 
      Setelah memilih opsi ini anda diminta memasukan speciality dokter.

      ![image](https://github.com/user-attachments/assets/a0dd0485-4f50-4f25-8d60-278450108135)

      Lalu program akan memunculkan jadwal dokter tersebut.

      ![image](https://github.com/user-attachments/assets/b70b7625-54ad-47d1-bfb8-ff0196ed889d)

    * [3] Mendisplay jadwal dokter.
 
      Setelah memilih opsi ini anda diminta memasukan hari.

      ![image](https://github.com/user-attachments/assets/0b3a1c41-a26d-4989-9015-73b67b9e19b4)

      Lalu program akan memunculkan jadwal pada hari tersebut.

      ![image](https://github.com/user-attachments/assets/4825ed1b-f71d-4843-ab4a-fdaf804050d5)

    * [0] kembali ke menu dokter.

**2.2 Login Sebagai Pasien**

Setelah berhasil login sebagai Pasien, program akan memastikan anda sudah login:

![image](https://github.com/user-attachments/assets/92dca48f-9951-4c2f-86bb-e9f1082ef490)

Setelah menekan tombol enter, anda akan diperlihatkan menu-menu berikut:

![image](https://github.com/user-attachments/assets/63d7132b-8b69-4090-85eb-a3dcb25e0f47)

* **[1] Doctor Management**:

    ![image](https://github.com/user-attachments/assets/30c605ab-b788-4c76-b843-b9181fa0d14c)

    * [1] Melihat dokter yang terakhir kali login.

      ![image](https://github.com/user-attachments/assets/c3ddab8d-be89-48fa-b0e4-b4c47fa96818)

    * [2] Melihat semua dokter yang sudah login.
 
      ![image](https://github.com/user-attachments/assets/43f55bf0-cd96-4469-b97c-6e96e9c7cb84)

    * [3] Mendisplay semua dokter yang ada di klinik daisuke.
 
      ![image](https://github.com/user-attachments/assets/53af34a3-e288-47d8-8a05-856aa64ce989)

    * [4] Mencari dokter melalui nama.
 
      Setelah memilih opsi ini anda akan diminta untuk memasuk nama dokter (nama lengkap).
      
      ![image](https://github.com/user-attachments/assets/4682eec6-399e-4ee0-bf40-7f0ca16629a5)

      Setelah itu akan memunculkan data dokter.
      
      ![image](https://github.com/user-attachments/assets/70ece636-3c76-41c9-97b5-d2830d52ae4e)

    * [5] Mencari dokter melaliu id.
 
      Setelah memilih opsi ini anda akan diminta untuk memasuk id dokter (hanya nomor).
      
      ![image](https://github.com/user-attachments/assets/229c5362-01c9-4f2b-ba8c-f105b3e1f272)

      Setelah itu akan menampilkan data dokter.
      
      ![image](https://github.com/user-attachments/assets/04c6e1b8-88c6-4554-85b1-229d9dda330f)

    * [0] Kembali ke menu pasien.

* **[2] Appointment Management**:
  
  ![image](https://github.com/user-attachments/assets/7acfd400-ad1b-4bab-8f99-cb17dc3a00ce)

    * [1] Mendisplay semua janji temu yang akan dilakukan.
 
      Setelah memilih opsi ini anda akan diminta untuk memasukkan Speciality Appointment, setelah itu program akan menampilkan semua Speciality Appointment yang belum dilakukan.

      ![image](https://github.com/user-attachments/assets/c73c73b0-4ab6-49df-82e4-ef02d11572ea)
 
    * [0] Kembali ke menu pasien.
      
* **[3] Medical Record Management**:
  
    * [1] Mencari Medical Record pasien melalui nama.

      setelah Memilih menu ini anda akan diminta memasukan nama pasien.
      
      ![image](https://github.com/user-attachments/assets/e378bad7-407e-4a5d-baa8-329f7ca3d45c)

      Setelah itu program akan menampilkan data Medicar Record dari pasien tersebut.

      ![image](https://github.com/user-attachments/assets/8b385ed8-09e7-499b-8737-0a1cdd7167c0)

    * [2] Mendisplay semua Medical Record.

      ![image](https://github.com/user-attachments/assets/1a5be561-4917-4dcb-bec5-e2144c61d6d9)

    * [0] Kembali ke menu pasien.
  
* **[4] Practice Schedule**:
  
    * [1] Mencari jadwal dokter dengan nama.
 
      Setelah memilih opsi ini anda diminta memasukan nama dokter.

      ![image](https://github.com/user-attachments/assets/40293621-ab49-4045-b2b7-c7b78e5d5a14)

      Lalu program akan memunculkan jadwal dokter tersebut.

      ![image](https://github.com/user-attachments/assets/e3554c5d-c1e3-4376-b79f-d84dd814d9bd)

    * [2] Mencari jadwal dokter dengan id.
 
      Setelah memilih opsi ini anda diminta memasukan speciality dokter.

      ![image](https://github.com/user-attachments/assets/a0dd0485-4f50-4f25-8d60-278450108135)

      Lalu program akan memunculkan jadwal dokter tersebut.

      ![image](https://github.com/user-attachments/assets/b70b7625-54ad-47d1-bfb8-ff0196ed889d)

    * [3] mendisplay jadwal dokter.
 
      Setelah memilih opsi ini anda diminta memasukan hari.

      ![image](https://github.com/user-attachments/assets/0b3a1c41-a26d-4989-9015-73b67b9e19b4)

      Lalu program akan memunculkan jadwal pada hari tersebut.

      ![image](https://github.com/user-attachments/assets/4825ed1b-f71d-4843-ab4a-fdaf804050d5)

    * [5] Kembali ke menu pasien.
      
* **[5] Log out**:

  Menyelesaikan program.

**2.3 Login Sebagai Admin**

Setelah berhasil login sebagai Admin, program akan memastikan anda sudah login:.

![image](https://github.com/user-attachments/assets/08aeff7f-2b3d-46a6-9fa5-a8dd05a9e5a4)

Setelah menekan tombol enter, anda akan diperlihatkan menu-menu berikut:

![image](https://github.com/user-attachments/assets/8e75a4d5-ef20-4c46-b081-a51773dcfdd4)

* **[1] Patient Management**:

    * [1] Menambahkan pasien.

      Setelah memilih opsi ini anda akan diminta mengisi data pasien.

      ![image](https://github.com/user-attachments/assets/56a1b948-7bb0-47b9-9b56-6105337cef44)

      Lalu setelah mengisi data maka program akan menampilkan.

      ![image](https://github.com/user-attachments/assets/7a9bbe41-4291-43df-816c-cfdf0f78f5b5)

    * [2] Menghapus akun pasien.

      Setelah memilih opsi ini anda akan diminta memasukan id pasien.

      ![image](https://github.com/user-attachments/assets/0399302c-0e17-45de-b33a-756541780eb0)

      Lalu program akan menghapus akun pasien tersebut
      
    * [3] Memungkinkan Anda membuat mencari pasien dari namanya.
      
      Masukan nama pasien yang ingin dicari.

      ![image](https://github.com/user-attachments/assets/1659eeb4-4d32-4911-82bd-d2824b80201f)

      Setelah memasukan nama akan muncul data pasien yang dicari
      
      ![image](https://github.com/user-attachments/assets/3845d50d-4273-47b5-9fc0-0b3276ecf086)

    * [4] Menunjukan semua pasien.
      
      ![image](https://github.com/user-attachments/assets/871edb48-3023-494c-a105-c08027d62408)

    * [5] Mencari pasien melaliu id.
      
      Setelah memilih opsi ini anda akan diminta memasukan nomor id pasien untuk mencari pasien tersebut.

      ![image](https://github.com/user-attachments/assets/8819941d-f4da-4b47-8fac-44e6661f2d82)

      Setelah memasukan id pasien, maka program akan menampilkan data pasien.
      
      ![image](https://github.com/user-attachments/assets/fd18c2ea-a7dc-497e-8a6f-2111af70fe52)

    * [6] Mendisplay pasien yang diurutkan melalui BST Inorder.
      
      ![image](https://github.com/user-attachments/assets/f5d885ab-825f-49cb-b797-9378e7a3a38a)

    * [7] Mendisplay pasien yang diurutkan melalui BST Preorder.
      
      ![image](https://github.com/user-attachments/assets/515de0db-2d1d-451e-9418-63d1b564ccf5)

    * [8] Mendisplay pasien yang diurutkan melalui BST Postorder.
      
      ![image](https://github.com/user-attachments/assets/1ad70a54-22de-46fd-a9c7-59bb2f37ce84)

    * [0] Kembali ke menu Admin.

  
* **[2] Doctor Management**:

    ![image](https://github.com/user-attachments/assets/30c605ab-b788-4c76-b843-b9181fa0d14c)

    * [1] Melihat dokter yang terakhir kali login.

      ![image](https://github.com/user-attachments/assets/c3ddab8d-be89-48fa-b0e4-b4c47fa96818)

    * [2] Melihat semua dokter yang sudah login.
 
      ![image](https://github.com/user-attachments/assets/43f55bf0-cd96-4469-b97c-6e96e9c7cb84)

    * [3] Mendisplay semua dokter yang ada di klinik daisuke.
 
      ![image](https://github.com/user-attachments/assets/53af34a3-e288-47d8-8a05-856aa64ce989)

    * [4] Mencari dokter melalui nama.
 
      Setelah memilih opsi ini anda akan diminta untuk memasuk nama dokter (nama lengkap).
      
      ![image](https://github.com/user-attachments/assets/4682eec6-399e-4ee0-bf40-7f0ca16629a5)

      Setelah itu akan memunculkan data dokter.
      
      ![image](https://github.com/user-attachments/assets/70ece636-3c76-41c9-97b5-d2830d52ae4e)

    * [5] Mencari dokter melaliu id.
 
      Setelah memilih opsi ini anda akan diminta untuk memasuk id dokter (hanya nomor).
      
      ![image](https://github.com/user-attachments/assets/229c5362-01c9-4f2b-ba8c-f105b3e1f272)

      Setelah itu akan menampilkan data dokter.
      
      ![image](https://github.com/user-attachments/assets/04c6e1b8-88c6-4554-85b1-229d9dda330f)

    * [0] Kembali ke menu pasien.

* **[3] Appointment Management**:
  
  ![image](https://github.com/user-attachments/assets/7acfd400-ad1b-4bab-8f99-cb17dc3a00ce)

    * [1] Mendisplay semua janji temu yang akan dilakukan.
 
      Setelah memilih opsi ini anda akan diminta untuk memasukkan Speciality Appointment, setelah itu program akan menampilkan semua Speciality Appointment yang belum dilakukan.

      ![image](https://github.com/user-attachments/assets/c73c73b0-4ab6-49df-82e4-ef02d11572ea)
 
    * [0] Kembali ke menu pasien.
      
* **[4] Medical Record Management**:
  
    * [1] Mencari Medical Record pasien melalui nama.

      Setelah memilih menu ini anda akan diminta memasukan nama pasien.
      
      ![image](https://github.com/user-attachments/assets/e378bad7-407e-4a5d-baa8-329f7ca3d45c)

      Setelah itu program akan menampilkan data Medicar Record dari pasien tersebut.

      ![image](https://github.com/user-attachments/assets/8b385ed8-09e7-499b-8737-0a1cdd7167c0)

    * [2] Mendisplay semua Medical Record.

      ![image](https://github.com/user-attachments/assets/1a5be561-4917-4dcb-bec5-e2144c61d6d9)

    * [0] Kembali ke menu pasien.
  
* **[5] Practice Schedule**:
    * [1] Mencari jadwal dokter dengan nama.
 
      Setelah memilih opsi ini anda diminta memasukan nama dokter.

      ![image](https://github.com/user-attachments/assets/40293621-ab49-4045-b2b7-c7b78e5d5a14)

      Lalu program akan memunculkan jadwal dokter tersebut.

      ![image](https://github.com/user-attachments/assets/e3554c5d-c1e3-4376-b79f-d84dd814d9bd)

    * [2] Mencari jadwal dokter dengan id.
 
      Setelah memilih opsi ini anda diminta memasukan speciality dokter.

      ![image](https://github.com/user-attachments/assets/a0dd0485-4f50-4f25-8d60-278450108135)

      Lalu program akan memunculkan jadwal dokter tersebut.

      ![image](https://github.com/user-attachments/assets/b70b7625-54ad-47d1-bfb8-ff0196ed889d)

    * [3] Mendisplay jadwal dokter.
 
      Setelah memilih opsi ini anda diminta memasukan hari.

      ![image](https://github.com/user-attachments/assets/0b3a1c41-a26d-4989-9015-73b67b9e19b4)

      Lalu program akan memunculkan jadwal pada hari tersebut.

      ![image](https://github.com/user-attachments/assets/4825ed1b-f71d-4843-ab4a-fdaf804050d5)

    * [5] Kembali ke menu pasien.
      
* **[6] Admin Logout**:

    ![image](https://github.com/user-attachments/assets/5847ed11-a3bf-4926-94d1-1254276db667)

    Admin di logout lalu menyelesaikan program.
  
**3. Register [2]**

![image](https://github.com/user-attachments/assets/91323e69-ab07-4d52-a433-974576f41567)

* **3.1 Register Doctor**:

  Setelah memilih register dokter `1` maka anda akan diminta mengisi data (nama, Speciality, dan password).
  
  ![image](https://github.com/user-attachments/assets/b5aca913-1ba7-427c-b119-2344e6952b55)

  Jika anda berasil register maka program akan menampilkan.

  ![image](https://github.com/user-attachments/assets/73237e1b-2b65-4402-ac7c-6ae047cb9bdf)

  Lalu anda akan dikembalikan ke menu utaama.

* **3.2 Register Admin**:

  Setelah memilih register dokter `2` maka anda akan diminta mengisi data (nama, dan password).
  
  ![image](https://github.com/user-attachments/assets/d076b554-2acf-451a-ac72-5335ab235f7c)

  Lalu anda akan dikembalikan ke menu utaama.

**4.Guest Account [3]**

Setelah menekan memilih opsi ini, anda akan diperlihatkan menu-menu berikut:

![image](https://github.com/user-attachments/assets/63d7132b-8b69-4090-85eb-a3dcb25e0f47)

* **[1] Doctor Management**:

    ![image](https://github.com/user-attachments/assets/30c605ab-b788-4c76-b843-b9181fa0d14c)

    * [1] Melihat dokter yang terakhir kali login.

      ![image](https://github.com/user-attachments/assets/c3ddab8d-be89-48fa-b0e4-b4c47fa96818)

    * [2] Melihat semua dokter yang sudah login.
 
      ![image](https://github.com/user-attachments/assets/43f55bf0-cd96-4469-b97c-6e96e9c7cb84)

    * [3] Mendisplay semua dokter yang ada di klinik daisuke.
 
      ![image](https://github.com/user-attachments/assets/53af34a3-e288-47d8-8a05-856aa64ce989)

    * [4] Mencari dokter melalui nama.
 
      Setelah memilih opsi ini anda akan diminta untuk memasuk nama dokter (nama lengkap).
      
      ![image](https://github.com/user-attachments/assets/4682eec6-399e-4ee0-bf40-7f0ca16629a5)

      Setelah itu akan memunculkan data dokter.
      
      ![image](https://github.com/user-attachments/assets/70ece636-3c76-41c9-97b5-d2830d52ae4e)

    * [5] Mencari dokter melaliu id.
 
      Setelah memilih opsi ini anda akan diminta untuk memasuk id dokter (hanya nomor).
      
      ![image](https://github.com/user-attachments/assets/229c5362-01c9-4f2b-ba8c-f105b3e1f272)

      Setelah itu akan menampilkan data dokter.
      
      ![image](https://github.com/user-attachments/assets/04c6e1b8-88c6-4554-85b1-229d9dda330f)

    * [0] kembali ke menu pasien.

* **[2] Appointment Management**:
  
  ![image](https://github.com/user-attachments/assets/7acfd400-ad1b-4bab-8f99-cb17dc3a00ce)

    * [1] Mendisplay semua janji temu yang akan dilakukan.
 
      Setelah memilih opsi ini anda akan diminta untuk memasukkan Speciality Appointment, setelah itu program akan menampilkan semua Speciality Appointment yang belum dilakukan.

      ![image](https://github.com/user-attachments/assets/c73c73b0-4ab6-49df-82e4-ef02d11572ea)
 
    * [0] Kembali ke menu pasien.
      
* **[3] Medical Record Management**:
  
    * [1] Mencari Medical Record pasien melalui nama.

      Setelah memilih menu ini anda akan diminta memasukan nama pasien.
      
      ![image](https://github.com/user-attachments/assets/e378bad7-407e-4a5d-baa8-329f7ca3d45c)

      Setelah itu program akan menampilkan data Medicar Record dari pasien tersebut.

      ![image](https://github.com/user-attachments/assets/8b385ed8-09e7-499b-8737-0a1cdd7167c0)

    * [2] Mendisplay semua Medical Record.

      ![image](https://github.com/user-attachments/assets/1a5be561-4917-4dcb-bec5-e2144c61d6d9)

    * [0] Kembali ke menu pasien.
  
* **[4] Practice Schedule**:
  
    * [1] Mencari jadwal dokter dengan nama.
 
      Setelah memilih opsi ini anda diminta memasukan nama dokter.

      ![image](https://github.com/user-attachments/assets/40293621-ab49-4045-b2b7-c7b78e5d5a14)

      Lalu program akan memunculkan jadwal dokter tersebut.

      ![image](https://github.com/user-attachments/assets/e3554c5d-c1e3-4376-b79f-d84dd814d9bd)

    * [2] Mencari jadwal dokter dengan id.
 
      Setelah memilih opsi ini anda diminta memasukan speciality dokter.

      ![image](https://github.com/user-attachments/assets/a0dd0485-4f50-4f25-8d60-278450108135)

      Lalu program akan memunculkan jadwal dokter tersebut.

      ![image](https://github.com/user-attachments/assets/b70b7625-54ad-47d1-bfb8-ff0196ed889d)

    * [3] Mendisplay jadwal dokter.
 
      Setelah memilih opsi ini anda diminta memasukan hari.

      ![image](https://github.com/user-attachments/assets/0b3a1c41-a26d-4989-9015-73b67b9e19b4)

      Lalu program akan memunculkan jadwal pada hari tersebut.

      ![image](https://github.com/user-attachments/assets/4825ed1b-f71d-4843-ab4a-fdaf804050d5)

    * [5] Kembali ke menu pasien.
      
* **[5] Log out**:

![image](https://github.com/user-attachments/assets/845420fd-7527-4523-ae53-2369fed5201e)

  Menyelesaikan program.
  
**5. Exit**;

![image](https://github.com/user-attachments/assets/0e9975d3-e920-4450-a349-f30199c75990)

  Menyelesaikan program.
