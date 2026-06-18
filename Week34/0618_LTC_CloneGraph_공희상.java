import java.util.HashMap;
import java.util.Map;

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> copiedNodes = new HashMap<>();

        return cloneNode(node, copiedNodes);
    }

    private Node cloneNode(Node originalNode, Map<Node, Node> copiedNodes) {
        if (copiedNodes.containsKey(originalNode)) {
            return copiedNodes.get(originalNode);
        }

        Node copiedNode = new Node(originalNode.val);
        copiedNodes.put(originalNode, copiedNode);

        for (Node originalNeighbor : originalNode.neighbors) {
            Node copiedNeighbor = cloneNode(originalNeighbor, copiedNodes);
            copiedNode.neighbors.add(copiedNeighbor);
        }

        return copiedNode;
    }
}
