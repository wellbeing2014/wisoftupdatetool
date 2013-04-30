package wisoft.pack.actions;

/**
 * Interface defining the application's command IDs.
 * Key bindings can be defined for specific commands.
 * To associate an action with a command, use IAction.setActionDefinitionId(commandId).
 *
 * @see org.eclipse.jface.action.IAction#setActionDefinitionId(String)
 */
public interface ICommandIds {

    public static final String PACKINFO_ADD="WiosftUpdatePack.add";
    public static final String PACKINFO_REMOVE="WiosftUpdatePack.remove";
    public static final String PACKINFO_OPEN="WiosftUpdatePack.open";
    public static final String PACKINFO_EXPORT="WiosftUpdatePack.export";
    public static final String PACKINFO_SAVE="WiosftUpdatePack.save";
    public static final String PACKINFO_DEPLOY="WiosftUpdatePack.deploy";
    public static final String PACKCONFIG_OPEN="WiosftUpdatePack.packconfig";
    
    
}
