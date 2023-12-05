package com.example.chickmed.data.model.faker

import com.example.chickmed.data.model.ArticleModel
import com.example.chickmed.data.model.DiseaseModel
import com.example.chickmed.data.model.ReportModel

object FakeDataSource {
    val dummyArticle = listOf(
        ArticleModel(
            1,
            "Siapa yang akan menjadi presiden di tahun 2024?",
            "Selaku Ketua Divisi Teknis Penyelenggaraan, Idham menyampaikan, KPU menetapkan tiga pasangan calon presiden dan wakil presiden dalam Pemilu Tahun 2024, yakni Anies Rasyid Baswedan-Muhaimin Iskandar, Ganjar Pranowo-Mahfud MD, serta Prabowo Subianto-Gibran Rakabuming Raka.",
            "https://thumb.viva.co.id/media/frontend/thumbs3/2022/10/05/633ce71ff1b87-menhan-prabowo-subianto_665_374.jpg",
            "13 Nov 2023",
        ),
        ArticleModel(
            2,
            "Apakah paus biru itu langka?",
            "Jumlah populasi paus biru di dunia diperkirakan antara 5.000 hingga 12.000 pada tahun 2002, meskipun banyak perkiraan yang masih belum pasti. Di dalam Daftar Merah IUCN, paus biru tergolong sebagai spesies yang \"terancam\".",
            "https://awsimages.detik.net.id/community/media/visual/2022/10/29/paus-biru-terdampar-di-raja-ampat-lokasi-dan-kondisi-terkini_169.jpeg",
            "20 Okt 2023",
        ),
        ArticleModel(
            3,
            "Roket fungsinya apa?",
            "Roket digunakan untuk kembang api, persenjataan, kursi penyelamat, kendaraan peluncur luar angkasa untuk Satelit buatan, kendaraan luar angkasa, dan eksplorasi ke planet lain.",
            "https://asset.kompas.com/crops/JsvA8I5tKNNCbntlqknt1SOjm6U=/0x89:1365x999/1200x800/data/photo/2022/02/11/62065d167fe78.jpg",
            "12 Okt 2023",
        ),
        ArticleModel(
            4,
            "Dinosaurus hidup di mana?",
            "Para ilmuan memperkirakan dinosaurus pertama kali muncul di Amerika Selatan. Namun, saat itu bumi masih berupa satu dataran besar (sebelum samudra terbentuk) dan dinamakan dengan Pangea. Diketahui bahwa dinosaurus adalah hasil evolusi archosaurus, reptil yang memiliki nenek moyang sama dengan aves dan buaya.",
            "https://asset.kompas.com/crops/SHETFCUOQR-CCe2HUl8_wA1ODa4=/47x0:947x600/750x500/data/photo/2022/06/06/629dbe971f133.jpg",
            "01 Okt 2023",
        ),
        ArticleModel(
            5,
            "Apa manfaat katak bagi manusia?",
            "Amfibi mempunyai banyak fungsi seperti untuk bahan konsumsi, alat uji medis dan bahan obat, juga sebagai predator berbagai serangga atau larva serangga. Katak yang di sawah diketahui memakan berbagai jenis serangga yang menjadi hama pertanian, hal ini penting dalam rantai makanan.",
            "https://bbksdajatim.org/wp-content/uploads/2016/08/Mycrohila-orientalis-Bali-kuku.jpg",
            "27 Sep 2023",
        ),
        ArticleModel(
            6,
            "Apa yang dimaksud dengan coding?",
            "Coding adalah menulis sekumpulan code sesuai aturan penulisan (syntax) tertentu dari bahasa pemrograman yang digunakan. Bahasa pemrograman ini ada bermacam-macam. Coding adalah satu di antara tindakan dari langkah-langkah pemrograman dengan menuliskan kode atau skrip dalam bahasa pemrograman.",
            "https://cdn1-production-images-kly.akamaized.net/HUHNMu7A870EWuUHYyycuDqHZWo=/800x450/smart/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/1590335/original/092899500_1494403408-Ilustrasi_coding__pemrograman__programmer__programming_Kredit_Pexels_via_Pixabay.jpg",
            "18 Sep 2023",
        ),
        ArticleModel(
            7,
            "Apa saja tujuan dari testing?",
            "Tujuan Testing itu apa? Testing merupakan proses uji coba untuk mengidentifikasi sebuah sistem agar mendapatkan hasil yang diharapkan. Dengan testing kita dapat mengidentifikasi kekurangan dalam perangkat lunak atau sistem sehingga dapat diperbaiki sebelum dilakukannya launching.",
            "https://www.gamelab.id/uploads/news/berita-667-software-tester-20210412-093350.png",
            "17 Sep 2023",
        ),
        ArticleModel(
            8,
            "Bagaimana cara membasmi rayap?",
            "Cara mengusir rayap yang ampuh adalah rutin menjemur perabot kayu dan barang berbahan kertas maupun kain pada tempat kering dan panas. Anda dapat menjemur perabot kayu kecil seperti meja dan kursi, kardus berisi buku, koran, majalah, dan sebagainya di terik matahari selama beberapa jam.",
            "https://awsimages.detik.net.id/community/media/visual/2015/09/10/43fd7131-77b4-4228-bdfc-4ff33a5464e0_169.jpg",
            "16 Sep 2023",
        ),
        ArticleModel(
            9,
            "Tame Impala",
            "Tame Impala adalah sebuah grup musik rock asal Australia yang terbentuk pada tahun 2007. Grup musik ini memiliki anggota yaitu Kevin Parker, Jay Watson, Dominic Simper, Cam Avery, dan Julien Barbagallo.",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Tame_Impala-3760_%2818222291464%29.jpg/800px-Tame_Impala-3760_%2818222291464%29.jpg",
            "15 Sep 2023",
        ),
        ArticleModel(
            10,
            "Siapa asisten Ancelotti?",
            "Davide bukan hanya penguntit nama besar ayahnya di bangku cadangan. Tetapi sosok peracik strategi Real Madrid. Ya, dia adalah asisten pelatih Carlo Ancelotti.",
            "https://asset.kompas.com/crops/siiLEOxe3xoG3BBziuRm-S054EM=/43x0:570x352/750x500/data/photo/2022/05/05/62736459db6ae.jpg",
            "14 Sep 2023",
        ),
        ArticleModel(
            11,
            "Nasi padang terdiri dari apa saja?",
            "Nasi padang merupakan nasi dengan sejumlah lauk, kuah kental, dan sayuran. Ciri khas menu nasi padang adalah gulai yang berisi kol, nangka muda, dan kacang panjang. Setelah itu, nasi padang dapat ditambahkan sejumlah lauk yang diolah dengan masakan khas padang.",
            "https://asset.kompas.com/crops/CHJoFQa1gAG3u2bYVXEliM_R9Mg=/120x88:920x622/750x500/data/photo/2020/09/25/5f6d572a3c1f9.jpg",
            "13 Sep 2023",
        ),
        ArticleModel(
            12,
            "Apa yang dimaksud dengan bau?",
            "Bau adalah zat kimia yang tercampur di udara, umumnya dengan konsentrasi yang sangat rendah, yang manusia terima dengan indra penciuman. Bau dapat berupa bau enak maupun tak enak.",
            "https://akcdn.detik.net.id/visual/2016/04/29/a8977674-4bb6-46af-8810-b5dc1ced6f6f_169.jpg",
            "12 Sep 2023",
        ),
        ArticleModel(
            13,
            "Apa yang dimaksud dengan darah?",
            "Darah adalah cairan yang terdapat pada semua makhluk hidup (kecuali tumbuhan) tingkat tinggi yang berfungsi mengirimkan zat-zat dan oksigen yang dibutuhkan oleh jaringan tubuh, mengangkut bahan-bahan kimia hasil metabolisme dan juga sebagai pertahanan tubuh terhadap virus atau bakteri.",
            "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2021/06/04035748/Darah.jpg",
            "11 Sep 2023",
        ),
    )

    val dummyReport = listOf(
        ReportModel(
            1,
            "29 Okt 2023",
            "https://arboge.com/wp-content/uploads/2014/11/Kotoran-Ayam-Mencret-Coklat.jpg",
            listOf(
                DiseaseModel(
                    1,
                    "Coccidiosis Salmonella Newcastle",
                    "Coccidiosis is a gastrointestinal illness caused by a protozoan. Clinical signs include diarrhea (with or without mucus), hematochezia, lethargy, weight loss, vomiting, signs of abdominal pain, pallor, and anorexia.",
                    "Pengobatan koksidiosis dapat dilakukan dengan pemberian obat-obatan yang bersifat koksidiostat atau koksidiosidal. Pemberian koksidiostat tidak mengeliminasi seluruh parasit dari dalam tubuh tetapi hanya menekan jumlah parasit yang ada di dalam tubuh."
                ),
                DiseaseModel(
                    1,
                    "Salmonella",
                    "Salmonella is a genus of rod-shaped gram-negative bacteria of the family Enterobacteriaceae. The two known species of Salmonella are Salmonella enterica and Salmonella bongori. S. enterica is the type species and is further divided into six subspecies that include over 2,600 serotypes.",
                    "Cara paling mudah untuk mengatasi infeksi Salmonella adalah minum banyak cairan untuk mencegah dehidrasi. Adapun jenis cairan yang bisa membantu tubuh tetap terhidrasi meliputi: Air mineral. Minuman olahraga atau isotonik."
                ),
                DiseaseModel(
                    1,
                    "Newcastle",
                    "Newcastle Disease atau yang sering disebut ND merupakan salah satu penyakit yang sangat ditakuti di industri broiler. Penyebabnya adalah avian paramyxovirus serotipe 1 (AMPV-1). ND menyerang burung liar dan unggas domestik yang biasanya muncul sebagai penyakit pernapasan.",
                    "Pencegahan Penyakit Pada Ayam Broiler\n" +
                            "Rutin memberikan multivitamin untuk memperkuat sistem imun.\n" +
                            "Penerapan biosecurity di sekitar area ternak.\n" +
                            "Memberikan probiotik untuk menekan bakteri patogen.\n" +
                            "Memberikan pakan dengan nutrisi yang tinggi.\n" +
                            "Menerapkan manajemen pengolahan kandang yang baik."
                ),
            ),
        ),
    )
}