Anatol Kaczmarek 156038

This is a program simulating a competition between two ant colonies. At the top of the screen there is score counter
indicating how much larvae was gathered and brought back to the anthill by blue and red ants respectively. At the center
of the screen there is an ants' world represented by a graph with nodes, places which ants can occupy, and edges, paths
ants can travel. In the bottom left corner we find the blue anthill and in the upper right corner the red one with many
nodes between them. White nodes are the basic ones, gray ones are stones and green ones are leaves. Next to some nodes
a yellow number can appear representing the amount of larvae that can be found there. At the beginning of the simulation
ants spawn in their respective anthills. Each ant follows a certain routine, which can consist of moving, attacking ants
from another colony or gathering larvae, depending on ant's class. Ants themselves are represented by little red and
blue squares inside nodes, when they move they disappear from one node and appear in another. Additionally, on the right side
of the screen there is a list of all ants currently present in the simulation, together with information about them.
Finally, at the bottom of the screen there are buttons allowing the user to add randomly generated red and blue ants to
the simulation

Upon executing the program user will be asked to specify some parameters:

- whether to run the simulation in freedom mode (look chapter about freedom mode for more details)
- the verbosity level of the logging system which tracks events happening in the simulation, all logs are printed to
  standard output
- number of vertices to randomly distribute on the graph
- number of randomly generated red ants to spawn at the beginning of the simulation
- number of randomly generated blue ants to spawn at the beginning of the simulation

Additional notes:

- Although the simulation supports a wide range of vertices and ants, it is the best suited for 20 vertices and 10 ants
  from each colony.
- Keep in mind that ants added with button during runtime adhere to the same limits of 30 ants per colony.
  That number refers to all ants ever present in the simulation i.e. both alive and dead.

Freedom mode is a special mode which applies a U.S. inspired theme to many aspects of the simulation:

- Vertices names are replaced with names of U.S. states
- Ants names are replaced with names of U.S. presidents and vice presidents belonging to the party associated with the
  ant's colour
- Blue anthill is replaced with a blue state, i.e. a state which traditionally votes for the Democratic Party
- Red anthill is replaced with a red state, i.e. a state which traditionally votes for the Republican Party
- Ants now collect dollars instead of larvae