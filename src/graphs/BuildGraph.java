package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

class ProjectMetaData {
	Set<String> depending = new HashSet<>();
	Set<String> dependants = new HashSet<>();
}

public class BuildGraph {

	private Map<String, ProjectMetaData> buildGraph = new HashMap<>();
	
	public void addDependecy(String parent, String child) {
		ProjectMetaData parentProject = getProjectEntry(parent);
		ProjectMetaData childProject = getProjectEntry(child);
		
		parentProject.dependants.add(child);
		childProject.depending.add(parent);
	}
	
	private ProjectMetaData getProjectEntry(String project) {
		ProjectMetaData projectMetaData = buildGraph.get(project);
		if (projectMetaData == null) {
			projectMetaData = new ProjectMetaData();
			buildGraph.put(project, projectMetaData);
		}
		
		return projectMetaData;
	}

	public List<String> buildOrder(BuildGraph graph) {
		List<String> buildOrder = new ArrayList<>();
		
		Queue<String> queue = new LinkedList<>();
		for (Entry<String, ProjectMetaData> entry : buildGraph.entrySet()) {
			if (entry.getValue().depending.size() == 0) {
				queue.add(entry.getKey());
			}
		}
		
		while (!queue.isEmpty()) {
			String currentProject = queue.remove();
			buildOrder.add(currentProject);
			
			for (String dependant : buildGraph.get(currentProject).dependants) {
				ProjectMetaData projectMetaData = buildGraph.get(dependant);
				projectMetaData.depending.remove(currentProject);
				if (projectMetaData.depending.isEmpty()) {
					queue.add(dependant);
				}
			}
		}
		
		return buildOrder;
	}

}



