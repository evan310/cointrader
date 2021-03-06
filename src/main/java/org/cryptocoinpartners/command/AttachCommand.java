package org.cryptocoinpartners.command;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

/**
 * @author Tim Olson
 */
@SuppressWarnings("UnusedDeclaration")
public class AttachCommand extends CommandBase {

    @Override
    public String getUsageHelp() {
        return "attach {base_module_class_name}";
    }

    @Override
    public String getExtraHelp() {
        return "Attaches a module class to this Context.  Classes may not be detached.";
    }

    @Override
    public void parse(String commandArguments) {
        args = commandArguments;
    }

    @Override
    public Object call() {

        if (StringUtils.isBlank(args)) {
            out.println("You must supply a base class name for the module class");
            return false;
        }
        try {
            context.attach(args);
            return true;
        } catch (Exception e) {
            out.println(e.getMessage());
            log.warn("Could not load module \"" + args + "\"", e);
            return false;
        }
    }

    @Inject
    Logger log;
    private String args;
}
