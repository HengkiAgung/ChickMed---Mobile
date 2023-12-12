package com.example.chickmed.data.model.faker

import com.example.chickmed.data.model.ArticleModel
import com.example.chickmed.data.model.DiseaseModel
import com.example.chickmed.data.model.ReportModel
import com.example.chickmed.data.model.UserModel

object FakeDataSource {
    val dummyArticle = listOf(
        ArticleModel(
            1,
            "Cara Merawat Ayam Petelur",
            "Merawat ayam petelur memerlukan perhatian khusus. Pastikan memberikan pakan berkualitas tinggi, menyediakan tempat bertelur yang nyaman, dan memeriksa kesehatan ayam secara teratur.",
            "https://upload.wikimedia.org/wikipedia/commons/3/3b/Gallus_gallus_domesticus_%28Parque_Quevedo%29.001_-_Leon.jpg",
            "13 Nov 2023",
        ),
        ArticleModel(
            2,
            "Pencegahan Penyakit Ayam",
            "Menjaga kebersihan kandang, memberikan vaksin yang sesuai, dan memisahkan ayam sakit adalah langkah-langkah penting dalam pencegahan penyakit pada ayam. Pelajari lebih lanjut tentang cara menjaga kesehatan ayam Anda.",
            "https://awsimages.detik.net.id/community/media/visual/2022/02/04/ayam-betina.jpeg?w=600&q=90",
            "20 Okt 2023",
        ),
        ArticleModel(
            3,
            "Mengenal Lebih Jauh tentang Flu Ayam",
            "Flu ayam adalah penyakit yang dapat merugikan peternakan ayam. Kenali gejala, pencegahan, dan langkah-langkah pengobatan yang perlu diambil jika ayam Anda terkena flu ayam.",
            "https://akcdn.detik.net.id/visual/2022/01/05/ilustrasi-hewan-ternak-ayam_169.jpeg?w=650",
            "12 Okt 2023",
        ),
        ArticleModel(
            4,
            "Cara Menangani Cacing pada Ayam",
            "Cacing adalah masalah umum pada ayam. Pelajari cara mencegah dan mengobati infestasi cacing pada ayam Anda untuk menjaga kesehatan dan produktivitasnya.",
            "https://cdn.idntimes.com/content-images/community/2022/02/ayam-jago-64112ad72a75e16df7c9d7b2e597a84d-7e7ddb5893569c35a9cff8cbb1b08117_600x400.jpg",
            "01 Okt 2023",
        ),
        ArticleModel(
            5,
            "Mengenal Berbagai Jenis Ayam Ras",
            "Ada banyak jenis ayam ras dengan karakteristik yang berbeda. Pilihlah jenis ayam ras yang sesuai dengan kebutuhan Anda, baik untuk petelur, daging, atau hobi.",
            "https://awsimages.detik.net.id/community/media/visual/2020/08/17/kakek-didenda-karena-ayam-berkokok.jpeg?w=650&q=80",
            "27 Sep 2023",
        ),
        ArticleModel(
            6,
            "Cara Mencegah Penularan Flu Burung",
            "Flu burung dapat menular pada ayam dan berdampak buruk pada peternakan. Ikuti langkah-langkah pencegahan yang efektif untuk melindungi ayam Anda dari flu burung.",
            "https://cdn1-production-images-kly.akamaized.net/PplBYAoAHY7a4kwiotNd-aR9Q0I=/1200x1200/smart/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/3544356/original/003868300_1629329825-james-wainscoat-yEW23jxVsNI-unsplash.jpg",
            "18 Sep 2023",
        ),
        ArticleModel(
            7,
            "Mengatasi Ayam yang Pingsan",
            "Ayam yang pingsan dapat menjadi tanda masalah kesehatan. Kenali penyebabnya dan terapkan tindakan penyelamatan untuk memulihkan kondisi ayam yang pingsan.",
            "https://www.mongabay.co.id/2022/07/07/sejak-kapan-ayam-dekat-dengan-kehidupan-manusia/",
            "17 Sep 2023",
        ),
        ArticleModel(
            8,
            "Cara Menjaga Kesehatan Kulit Ayam",
            "Kesehatan kulit ayam mempengaruhi kesejahteraannya secara keseluruhan. Pelajari cara menjaga kebersihan dan kesehatan kulit ayam agar tetap aktif dan produktif.",
            "https://www.merdeka.com/dunia/bagian-mana-dalam-telur-yang-bakal-jadi-anak-ayam.html",
            "16 Sep 2023",
        ),
        ArticleModel(
            9,
            "Ciri-ciri Ayam Sehat",
            "Mengenali ciri-ciri ayam yang sehat adalah kunci untuk menjaga produktivitas peternakan. Perhatikan tanda-tanda kesehatan ayam untuk mendeteksi masalah sejak dini.",
            "https://cdn1.katadata.co.id/media/images/thumb/2023/11/03/Ayam_Berkokok_di_Malam_Hari_Jam_12-2023_11_03-14_54_37_479fd1c7d4f7143b3ecf4668614a487a_960x640_thumb.png",
            "15 Sep 2023",
        ),
        ArticleModel(
            10,
            "Cara Mengatasi Ayam yang Stres",
            "Stres dapat mempengaruhi kesehatan dan produktivitas ayam. Temukan cara mengidentifikasi tanda-tanda stres pada ayam dan terapkan strategi penanganan yang efektif.",
            "https://static.promediateknologi.id/crop/0x0:0x0/750x500/webp/photo/2023/01/23/2553068192.jpg",
            "14 Sep 2023",
        ),
    )
//
//    val dummyReport = listOf(
//        ReportModel(
//            1,
//            "29 Okt 2023",
//            "https://arboge.com/wp-content/uploads/2014/11/Kotoran-Ayam-Mencret-Coklat.jpg",
//            listOf(
//                DiseaseModel(
//                    1,
//                    "Coccidiosis Salmonella Newcastle",
//                    "Coccidiosis is a gastrointestinal illness caused by a protozoan. Clinical signs include diarrhea (with or without mucus), hematochezia, lethargy, weight loss, vomiting, signs of abdominal pain, pallor, and anorexia.",
//                    "Pengobatan koksidiosis dapat dilakukan dengan pemberian obat-obatan yang bersifat koksidiostat atau koksidiosidal. Pemberian koksidiostat tidak mengeliminasi seluruh parasit dari dalam tubuh tetapi hanya menekan jumlah parasit yang ada di dalam tubuh."
//                ),
//                DiseaseModel(
//                    1,
//                    "Salmonella",
//                    "Salmonella is a genus of rod-shaped gram-negative bacteria of the family Enterobacteriaceae. The two known species of Salmonella are Salmonella enterica and Salmonella bongori. S. enterica is the type species and is further divided into six subspecies that include over 2,600 serotypes.",
//                    "Cara paling mudah untuk mengatasi infeksi Salmonella adalah minum banyak cairan untuk mencegah dehidrasi. Adapun jenis cairan yang bisa membantu tubuh tetap terhidrasi meliputi: Air mineral. Minuman olahraga atau isotonik."
//                ),
//                DiseaseModel(
//                    1,
//                    "Newcastle",
//                    "Newcastle Disease atau yang sering disebut ND merupakan salah satu penyakit yang sangat ditakuti di industri broiler. Penyebabnya adalah avian paramyxovirus serotipe 1 (AMPV-1). ND menyerang burung liar dan unggas domestik yang biasanya muncul sebagai penyakit pernapasan.",
//                    "Pencegahan Penyakit Pada Ayam Broiler\n" +
//                            "Rutin memberikan multivitamin untuk memperkuat sistem imun.\n" +
//                            "Penerapan biosecurity di sekitar area ternak.\n" +
//                            "Memberikan probiotik untuk menekan bakteri patogen.\n" +
//                            "Memberikan pakan dengan nutrisi yang tinggi.\n" +
//                            "Menerapkan manajemen pengolahan kandang yang baik."
//                ),
//            ),
//        ),
//    )

    val dummyUser = UserModel(
        1,
        "Risky",
        "Risky@mail.com",
        "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg",
        "sdawflkjslkdfjsda",
    )
}