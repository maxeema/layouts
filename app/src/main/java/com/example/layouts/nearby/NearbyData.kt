import com.example.layouts.nearby.NearbyGroup
import com.example.layouts.nearby.NearbyItem

val nearbyData = listOf(
    NearbyGroup(
        "hospital", name = "Hospital", items = listOf(
            NearbyItem("gdk1", "GDK Hospital"),
            NearbyItem("phenom1", "Phenom Hospital"),
            NearbyItem("hospital1", "Hospital Crown"),
            NearbyItem("gdk2", "GDK Hospital"),
            NearbyItem("phenom2", "Phenom Hospital"),
            NearbyItem("hospital2", "Hospital Crown"),
            NearbyItem("gdk3", "GDK Hospital"),
            NearbyItem("phenom3", "Phenom Hospital"),
            NearbyItem("hospital3", "Hospital Crown"),
        )
    ),
    run {
        NearbyGroup("labs", name = "Labs", items = buildList {
            repeat(15) { idx -> add(NearbyItem("lab$idx", "GDK Lab")) }
        })
    },
    run {
        NearbyGroup("doctor", name = "Doctor", items = buildList {
            repeat(15) { idx -> add(NearbyItem("doctor$idx", "Dr. Ankita Panda")) }
        })
    },
)