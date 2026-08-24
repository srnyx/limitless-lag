plugins {
    java
    id("xyz.srnyx.gradle-galaxy") version "a8227b9"
    id("com.gradleup.shadow") version "9.6.1"
    id("me.modmuss50.mod-publish-plugin") version "675051c"
    id("io.papermc.hangar-publish-plugin") version "0.1.4"
    id("xyz.jpenilla.run-paper") version "3.1.0"
}

group = "xyz.srnyx"
description = "Simulate lag for players on your server"

galaxy {
    minecraft {
        spigotAPI("1.8.8")
        annoyingAPI("45ae893")

        dependency {
            optional {
                repositories.add(PLACEHOLDER_API)
                group = "me.clip"
                artifact = "placeholderapi"
                version = "2.12.2"

                pluginYml = "PlaceholderAPI"
                modrinth = "placeholderapi"
                hangar = "PlaceholderAPI"
            }
        }

        pluginYml {
            developerData(SRNYX)

            command("lagreload") {
                aliases.add("limitlesslagreload")
                description = "Reloads the plugin"

                permission("reload")
            }
            command("lag") {
                aliases.addAll("laggy", "laggify", "limitlesslag")
                description = "Toggles lag for yourself or the specified player"

                permission("command")
            }
        }

        platformPublishing {
            github("srnyx/limitless-lag")
            modrinth("TwwhAG4s")
            hangar("LimitlessLag")
            spigot("109420")
            curseforge("896851")

            projectData("limitless-alg")
        }
    }
}
