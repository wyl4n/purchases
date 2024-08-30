plugins {
    java

    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"

    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "net.singlenetwork.farm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven ("https://oss.sonatype.org/content/groups/public/")
    maven ("https://repo.aikar.co/content/groups/aikar/")
    maven( "https://repo.extendedclip.com/content/repositories/placeholderapi/" )
    maven ("https://libraries.minecraft.net/")

    maven( "https://jitpack.io")
}

dependencies {


    compileOnly ("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly ("com.github.azbh111:craftbukkit-1.8.8:R")

    implementation("com.zaxxer:HikariCP:4.0.3")

    implementation ("co.aikar:acf-paper:0.5.1-SNAPSHOT")

    compileOnly("me.clip:placeholderapi:2.11.6")

    compileOnly("org.jetbrains:annotations:23.0.0")

    compileOnly ("org.projectlombok:lombok:1.18.20")
    annotationProcessor ("org.projectlombok:lombok:1.18.20")
    implementation (fileTree("libs"))

}
bukkit {
    name = "purchases"
    author = "wylan"
    main = "net.singlenetwork.farm.purchases.PurchasesPlugin"
    version = "0.0.1"
}
tasks {
    java {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    compileJava { options.encoding = "UTF-8"}

    java {
        shadowJar {
            archiveFileName.set("purchases.jar")

            relocate(
                "me.devnatan.inventoryframework",
                "net.singlenetwork.farm.purchases.libs.inventory"
            )
        }
    }
}

