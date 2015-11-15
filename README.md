# graph
Common graph path finder

This is implementation of method which takes root-node and two specified nodes from one tree-graph as params and computes the minimal common parent node of last two nodes. Please note that this algorithm can be improved by implementing a shared flag which should be enabled after detection of both nodes while iterating over the tree. After flag was enabled all map operations should be swittched to 'return null' operations.
