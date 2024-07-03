# PugBlocker
PugBlocker - a plugin for Minecraft designed to manage player rights via LuckPerms. Restricts player actions such as movement, block placement, chat, bucket usage and many others based on custom permissions. It has flexible configuration via a configuration file for managing messages and rights, and supports reloading the configuration via a command.

## ScreenShot
![image](https://i.imgur.com/b2rhJXH.png,"screenshot")

## How to use
1. [Download Releases](https://github.com/MopsLops/PugBlocker/releases)
2. [Download Luckperms](https://luckperms.net/download)
3. Place PugBlocker.jar and Luckperms.jar in your server plugins folder
4. Run the Server on version 1.21 or higher

## Requirements
* Server version 1.21 or newer
* Core Paper/Spigot
* Java 21 JDK or newer
* [Luckperms](https://luckperms.net/download)

## Permissions
1. pugblocker.reload

   *Description Allows reloading the PugBlocker configuration.
   *Default OP

2. pugblocker

   *Description Base permission for all PugBlocker permissions.
   *Default False

3. pugblocker.*

   *Description Grants access to all PugBlocker permissions.
   *Default False

4. pugblocker.move

   *Description Allows the player to move.
   *Default False

5. pugblocker.break

   *Description Allows the player to break blocks.
   *Default False

6. pugblocker.place

   *Description Allows the player to place blocks.
   *Default False

7. pugblocker.nearplace

   *Description Allows the player to build near other blocks.
   *Default False

8. pugblocker.chat

   *Description Allows the player to chat.
   *Default False

9. pugblocker.drop

   *Description Allows the player to drop items.
   *Default False

10. pugblocker.pickup

    *Description Allows the player to pick up items.
    *Default False

11. pugblocker.interact

    *Description Allows the player to interact with blocks/entities.
    *Default False

12. pugblocker.inventory.click

    *Description Allows the player to click in the inventory.
    *Default False

13. pugblocker.inventory.drag

    *Description Allows the player to drag items in the inventory.
    *Default False

14. pugblocker.inventory.open

    *Description Allows the player to open the inventory.
    *Default False

15. pugblocker.craft

    *Description Allows the player to craft items.
    *Default False

16. pugblocker.bucket

    *Description Allows the player to use buckets.
    *Default False

17. pugblocker.open

    *Description Allows the player to open blocks.
    *Default False

18. pugblocker.plate

    *Description: Allows the player to step on pressure plates.
    *Default: False

19. pugblocker.piston

    *Description: Allows the player to use pistons.
    *Default False

20. pugblocker.attack

    *Description Allows the player to attack entities.
    *Default False

## Config.yml
```
# Whether permissions should apply to operators (ops) on the server.
applyToOps: true

# Custom messages used in the plugin.
messages:
  noPermission: "You do not have permission to execute this command."
  configReloaded: "PugBlocker config reloaded!"

  chatNoPermission: "You do not have permission to chat."
```


## Author
* Mops_Lops
* YouTube: [@MopsLops](https://www.youtube.com/channel/UCvpPkpVh0ocwRMfpy5pEaPw)
* Twitch: [@mops_lops](https://www.twitch.tv/mops_lops)
* Instagram: [@mops_lops](https://www.instagram.com/mops_lops/)
* Discord: [Гвукамоле](https://discord.com/invite/PRvBJRt)
 
## License
This app is under the [MIT license](https://github.com/MopsLops/PugBlocker/blob/main/LICENSE)
