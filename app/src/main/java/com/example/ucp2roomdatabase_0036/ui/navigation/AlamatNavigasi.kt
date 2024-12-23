package com.example.ucp2roomdatabase_0036.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHomeDokter : AlamatNavigasi {
    override val route = "home_dokter"
}

object DestinasiInsertDokter : AlamatNavigasi {
    override val route = "insert_dokter"
}



object DestinasiHomeJadwal : AlamatNavigasi {
    override val route: String = "home_jadwal"
}

object DestinasiInsertJadwal : AlamatNavigasi {
    override val route = "insert_jadwal"
}
object DestinasiDetailJadwal : AlamatNavigasi {
    override val route = "detail"
    const val idJadwal = "id"
    val routeWithArgs = "$route/{$idJadwal}"
}
object DestinasiUpdateJadwal : AlamatNavigasi {
    override val route = "update"
    const val idJadwal = "id"
    val routeWithArgs = "$route/{$idJadwal}"
}