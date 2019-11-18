package phases;

import java.util.ArrayList;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.properties.Property;

import helper.Help;
import layeredLayouting.options.LayeredLayoutingOptions;
import properties.NodeProperty;

public class NodePlacementLayerPhase implements LayerPhase {

    @Override
    public void apply(ElkNode layoutGraph, IElkProgressMonitor monitor) throws Exception {
        double nodeNodeSpacing = layoutGraph.getProperty(LayeredLayoutingOptions.SPACING_NODE_NODE);
        var nodes = layoutGraph.getChildren();
        var layers = Help.getGraphProp(layoutGraph).layers;
        
        double curX = 0, curY = 0;
        for (var l : layers)
        {
            double stackWidth = l.stream().map(x -> x.getWidth()).max(Double::compare).get();
            curY = 0;
            for (var n : l)
            {
                n.setX(curX);
                n.setY(curY);
                
                curY += n.getHeight() + nodeNodeSpacing;
            }
            curX += stackWidth + nodeNodeSpacing;
        }
    }

}