import ore.OreSim;

import java.util.*;

public
class OreTestData {
    Map<OreSim.ElementType, Set<String>> actorLocations = new HashMap<>();
    int stepNumber;
    private List<String> convertActorComponent(String actorComponent) {
        String[] components = actorComponent.split(":");
        if (components.length > 1) {
            String[] locationComponents = components[1].split(",");
            return Arrays.asList(locationComponents);
        }

        return new ArrayList<>();
    }
    public OreTestData(int stepNumber, String dataLine) {
        this.stepNumber = stepNumber;
        String[] actorComponents = dataLine.split("#");
        for (int i = 1; i < actorComponents.length; i++) {
            String actorComponent = actorComponents[i];
            String actorType = actorComponent.split(":")[0];
            OreSim.ElementType elementType = OreSim.ElementType.getElementByShortType(actorType);
            Set<String> locations = new HashSet<>(convertActorComponent(actorComponent));
            actorLocations.put(elementType, locations);
        }
    }
}