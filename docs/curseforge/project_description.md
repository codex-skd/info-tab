<h1 align="center">&#128172; Info TAB</h1>

<p align="center"><strong>Player info at a glance &mdash; dimension, AFK, and more in the tab list.</strong></p>

<p align="center">
<img src="https://img.shields.io/badge/loader-NeoForge-orange?style=plastic&logo=curseforge" alt="NeoForge">
<img src="https://img.shields.io/badge/minecraft-26.2%20%7C%201.21.1-blue?style=plastic" alt="Minecraft 26.2 and 1.21.1">
<img src="https://img.shields.io/badge/side-client%20%2B%20server-brightgreen?style=plastic" alt="Client and Server">
<img src="https://img.shields.io/badge/config-in--game%20%2F%20screen-lightgrey?style=plastic" alt="In-game config">
</p>

<br>

---

<br>

<h2>&#10024; Overview</h2>

<table>
<tr>
<td width="65%">
<p>Info TAB shows each player's current <strong>dimension</strong> and <strong>AFK status</strong> right in the tab list. See who's away, where everyone is, and never ask "where is everyone?" again. Per-dimension colours, custom aliases, a token-based display format and a runtime <code>/infotab</code> command make it fully configurable without a restart. An <code>[AFK]</code> tag also appears above inactive players' heads.</p>

<p>An original mod by <strong>Stalking Dragons</strong>, inspired by <em>DimensionViewer</em> by <em>Sick Stick 10</em>. It adds no gameplay content &mdash; only tab-list and name-tag information.</p>
</td>
<td width="35%" align="center">
<a href="https://codex.skdragons.com/" target="_blank"><img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/logo_codex_stalking_dragons.png" alt="Codex Stalking Dragons" width="160"></a>
</td>
</tr>
</table>

<br>

<h2>&#127919; Features</h2>

<h3>&#128716;&#65039; AFK Detection</h3>
<p>Players inactive for 10+ minutes get an <code>[AFK]</code> prefix (creme colour) in the tab list and above their head. Activity resets on movement, world interaction, chat, combat, mouse look, mouse clicks or key presses.</p>

<h3>&#127912; Per-Dimension Colours</h3>
<p>Green for the Overworld, red for the Nether, purple for the End &mdash; and any colour you want for modded dimensions, via Minecraft colour names or hex codes.</p>

<h3>&#128221; Customisable Format</h3>
<p>Build your own display with tokens: <code>%dim:name%</code>, <code>%dim:id%</code>, <code>%dim:namespace%</code>, <code>%dim:path%</code>. Default: <strong>PlayerName [%dim:name%]</strong>.</p>

<h3>&#127991;&#65039; Dimension Aliases</h3>
<p>Give dimensions friendly names &mdash; show "The Aether" instead of <code>aether:the_aether</code>.</p>

<h3>&#128208; Flexible Placement</h3>
<p>Put the dimension tag before (prepend) or after (append) the player name.</p>

<h3>&#9881;&#65039; Runtime Configuration</h3>
<p>Full config via the <code>/infotab</code> command &mdash; colours, aliases, format, placement &mdash; plus a config screen in the Mods menu. No restart needed.</p>

<br>

<h2>&#129521; Mod Structure</h2>

<table>
<tr><th align="left">Area</th><th align="left">What it provides</th></tr>
<tr><td><code>PlayerListHandler</code></td><td>Builds the coloured, tokenised tab-list display name for each player.</td></tr>
<tr><td><code>AfkTracker</code> / <code>AfkNameTagHandler</code> / <code>AfkActivityClientHandler</code></td><td>Server-side inactivity tracking, the name-tag <code>[AFK]</code> overlay, and client input detection.</td></tr>
<tr><td><code>CustomCommands</code></td><td>The <code>/infotab</code> command tree for runtime configuration.</td></tr>
<tr><td><code>Config</code> / <code>platform</code></td><td>The config model and the config-screen / config-helper service layer.</td></tr>
<tr><td><code>network</code></td><td>The client&rarr;server activity ping used to reset AFK on look-only input.</td></tr>
</table>

<br>

<h2>&#128203; Requirements</h2>

<table>
<tr><td><strong>Minecraft / NeoForge / Java</strong></td><td>see <em>Available Versions</em> below</td></tr>
<tr><td><strong>Dependencies</strong></td><td>None</td></tr>
<tr><td><strong>Side</strong></td><td>Client and Server (client required for the name-tag overlay and input-based activity detection)</td></tr>
</table>

<br>

<h2>&#128230; Available Versions</h2>

<table>
<tr><th align="left">Minecraft</th><th align="left">NeoForge</th><th align="left">Java</th><th align="left">Latest build</th><th align="left">Status</th></tr>
<tr><td>26.2</td><td>26.2.0.57+</td><td>25</td><td><code>1.1.0</code></td><td>Stable</td></tr>
<tr><td>1.21.1</td><td>21.1.249+</td><td>21</td><td><code>0.0.0-beta.1</code></td><td>Beta &mdash; API port from the 26.2 line</td></tr>
</table>

<p><em>Both versions share this CurseForge project. Pick the file that matches your Minecraft version.</em></p>

<br>

<h2>&#127918; Commands</h2>

<ul>
<li><code>/infotab color &lt;dimension&gt; &lt;color&gt;</code> &mdash; set a dimension colour</li>
<li><code>/infotab alias &lt;dimension&gt; &lt;alias&gt;</code> &mdash; set a custom name</li>
<li><code>/infotab format &lt;format&gt;</code> &mdash; change the display format</li>
<li><code>/infotab placement prepend|append</code> &mdash; tag position</li>
<li><code>/infotab refresh</code> &mdash; refresh all player names</li>
<li><code>/infotab reload</code> &mdash; reload the config</li>
<li><code>/infotab dimid</code> &mdash; show your dimension ID</li>
<li><code>/infotab list colors|aliases</code> &mdash; list custom settings</li>
</ul>

<br>

---

<br>

<h2>&#128591; Credits</h2>

<p>An original mod by <strong>Stalking Dragons</strong>, inspired by <em>DimensionViewer</em> by <em>Sick Stick 10</em>.</p>

<br>
<br>

<p align="center">
  <a href="https://codex.skdragons.com/" target="_blank">
    <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/logo_codex_stalking_dragons.png" alt="Codex Stalking Dragons" width="200">
  </a>
  <br>
  <a href="https://codex.skdragons.com/">https://codex.skdragons.com/</a>
  <br>
  <em>Codex Stalking Dragons &mdash; Minecraft Modding</em>
</p>
