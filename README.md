# WaterSafeWither

A Spigot 1.8.8 plugin that protects builds from Wither-related destruction. Prevents water/lava flow near Withers, controls Wither block-breaking, and optionally drops spawners with configurable behavior.

## Features

- **Prevent Water Break** — Stops the Wither from destroying water-related blocks
- **Prevent Water Flow** — Blocks water/lava from flowing within a configurable radius of a Wither
- **Drop Spawners** — Wither explosions can drop spawners with a configurable chance
- **Spawner Float Up** — Dropped spawners float upward for easier collection

## Commands

| Command      | Description                     | Permission              |
|--------------|---------------------------------|-------------------------|
| `/wswreload` | Reload the plugin configuration | `watersafewither.reload` |

## Configuration

```yaml
features:
  prevent-water-break: true
  prevent-water-flow: false
  drop-spawners: true
  spawner-float-up: true

tuning:
  spawner-float-velocity-y: 0.35
  wither-flow-radius: 20.0
  spawner-drop-chance: 0.5
```

| Key                        | Type    | Default | Description                                      |
|----------------------------|---------|---------|--------------------------------------------------|
| `prevent-water-break`      | boolean | `true`  | Prevent Wither from breaking water-related blocks |
| `prevent-water-flow`       | boolean | `false` | Block water/lava flow near Withers                |
| `drop-spawners`            | boolean | `true`  | Enable spawner drops from Wither explosions       |
| `spawner-float-up`         | boolean | `true`  | Make dropped spawners float upward                |
| `spawner-float-velocity-y` | double  | `0.35`  | Upward velocity for floating spawners             |
| `wither-flow-radius`       | double  | `20.0`  | Radius around Wither to block fluid flow          |
| `spawner-drop-chance`      | double  | `0.5`   | Chance (0.0–1.0) for a spawner to drop            |

## Installation

1. Download `WaterSafeWither-<version>.jar` from [Releases](../../releases)
2. Place the JAR in your server's `plugins/` folder
3. Restart the server
4. Edit `plugins/WaterSafeWither/config.yml` to your liking
5. Run `/wswreload` to apply changes

## Building

Requires **JDK 17** and **Maven**.

```bash
mvn clean package -DskipTests
```

The output JAR will be in `target/WaterSafeWither-<version>.jar`.

## CI/CD

- **Build** — Runs on every push to `master` / PR: Spotless (ktlint) → Compile → SpotBugs → Package
- **Release** — Triggered by pushing a `v*` tag: full pipeline + GitHub Release + Discord notification

## License

[MIT](LICENSE)
