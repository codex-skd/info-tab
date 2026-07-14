# Dimension Tab

Mod server-side para NeoForge que muestra la dimensión actual de cada jugador en la lista de jugadores (tab list).

## Características

- Muestra la dimensión de cada jugador en el tab list
- Colores personalizables por dimensión (Overworld, Nether, End, y dimensiones de mods)
- Formato configurable con tokens: `%dim:name%`, `%dim:id%`, `%dim:namespace%`, `%dim:path%`
- Aliases para dimensiones (nombres personalizados)
- Posición del tag configurable: antes o después del nombre
- Comando `/dimensiontab` para configuración en tiempo real
- Pantalla de configuración en el menú de mods (solo cliente)
- Sin dependencias externas

## Requisitos

- **NeoForge** 26.1.2.76 o superior
- **Minecraft** 26.1.2

## Instalación

1. Descarga el JAR desde CurseForge
2. Colócalo en la carpeta `mods` del servidor
3. Los clientes no necesitan instalar nada

## Compilación

```bash
./gradlew.bat build
```

El JAR se genera en `build/libs/dimensiontab-<version>.jar`.
