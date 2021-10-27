package liqp.tags;

import liqp.TemplateContext;
import liqp.nodes.LNode;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Stack;

import static liqp.TemplateContext.REGISTRY_FILENAME_STACK;

public class IncludeRelative extends Include {
    public IncludeRelative() {
        super("include_relative");
    }

    @Override
    public Object render(TemplateContext context, LNode... nodes) {
        return super.render(context, nodes);
    }

    @Override
    protected File getIncludeResourceFile(TemplateContext context, String includeResource, String extension) {
        Map<String, Stack<Path>> registry = context.getRegistry(REGISTRY_FILENAME_STACK);
        Stack<Path> fileStack = registry.get(REGISTRY_FILENAME_STACK);
        //noinspection Java8MapApi
        if (fileStack == null) {
            fileStack = new Stack<>();
            registry.put(REGISTRY_FILENAME_STACK, fileStack);
        }
        Path includesDirectory;
        if (fileStack.isEmpty()) {
            includesDirectory = Paths.get("");
        } else {
            includesDirectory = fileStack.peek();
        }
        
        return Paths.get(includesDirectory.toAbsolutePath().toString(), includeResource + extension).toFile();
    }
}
