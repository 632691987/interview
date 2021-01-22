package blog.csdn.net;

import java.util.ArrayList;
import java.util.List;

public class Sample1 {

    private NodeMapper nodeMapper;

    public void getAllChildren(int parentNodeId, List<String> allChildren) {
        List<String> children = getChildren(parentNodeId);
        allChildren.addAll(children);
    }

    public List<String> getChildren(int nodeId) {
        List<String> children = nodeMapper.getChildren(nodeId);
        return children;
    }

    class NodeMapper {
        public List<String> getChildren(int nodeId) {
            return new ArrayList<>();
        }
    }
}


