# Coding Documentation
I'm going to be honest, this is not really coding documentation, this is more just a recording of decisions that I made during development that I wrote down so that I remember them.
But it may also be helpful to anyone trying to understand my absolute insanity.
Tho to be fair all the other files in this directory just seem like an insane person rambling.

So is everything in this directory, all files here are just me trying to remember things, don't think too much about them.

## Stuff I just wanted to write down
- Data Generation will be used for advancements, loot-tables, recipes & tags (where possible)
- A lot of Models will be done with Data Generation, but obviously custom/complex Models will remain as Json/Blender Files
- Translation will be done with lang files
- Don't forget to add EMI & JEI compatibility once they port to whatever version we are at
- This mod will probably never support multiple version explicitly, though we'll likely keep version for older MC version in other branches for archival purpose
- The Logo is actually just the fabric logo above the NTM Wiki logo
- If you look at the mod logo, you will see that it is not centered because the fabric logo would block too much of the NTM logo otherwise

# *"Brief"* Explanations of how some of the Features work

## Node Networks
As a brief explanation, in the original Mod "Node Networks" simply referred to any system using "Nodespace" which was essentially a
shadow dimension saved to a world file in which things like locations of cables & pipes would be stored in order to be quickly accessed.
In my version of Node Networks there is no such thing as Nodespace, instead there is NodeNetworks that keep track of all loaded Nodes which are identified by UUIDs,
all a node in a network has to do is store the UUID of it's assigned Network once it is unloaded so it can be loaded again once the Node is loaded.
When a Node is placed it checks it neighbour blocks for Nodes, if it finds one then this Node will use its Network & if it finds multiple, it will merge all the Networks Together.
Once a Node is Removed, it will check if it was Connecting Multiple Nodes, if it was then it Splits the Network at its current location
(if the Network is large then this can take some time (we are talking about a few ms), this is the one Downside of not having Nodespace as that would speed up this process).
A Node in this context refers to a BlockEntity.
A Node contains a Collection of NodeValueContainers which handle things like contained Energy or Fluid & whether a node is inserting into or taking from the Network.

## Advanced Models (aka: Blender Models / Wavefront OBJ Files)
OBJ Files are loaded from the Resource Manager (technically you could replace them with a resource pack, thou you would have to restart your game as there is no code for reloading them).
They are converted to a "MultiModel3D" which contains multiple "GroupedModel3D" which then contain multiple "Model3D", this follows OBJ file standards, they are GroupedModel3D are grouped by object and Model3D are grouped by group.
When Rendered a Model is added to a BufferBuilder and then send to the TexturedRenderPipeline in ModRenderPipelines which uses a custom shader code that is basically just the Block Render shader, but I renamed it.
These Models can then be Rendered whenever needed. The Current Way they are rendered is fucking horrible, so I still have to think of a way to do that properly.
Currently, all things that want to have advanced Models need to make a block entity and give it a block entity render and that render needs to call .render() on the model,
this is extremely laggy, as in: 1000 Alloy Furnace Extensions bring my computer from  ~500 Fps to 10.
However, after looking into this, this simply appears to be a SkillIssue considering the original does this exactly the same & they have no lag problem. maybe it's some post 1.12 type shit, I have no clue.
