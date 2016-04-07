package org.cimi.designer;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cimi.designer.antlr.odinLexer;
import org.cimi.designer.antlr.odinParser;

import java.io.*;

/**
 * Created by cnanjo on 3/31/16.
 */
public class ReferenceModelLoader {

    private static Logger log = LogManager.getLogger(ReferenceModelLoader.class.getName());

    public ReferenceModelVisitor loadReferenceModel(String bmmFilePath) {
        File file = new File(bmmFilePath);
        ReferenceModelVisitor visitor = null;
        try (FileInputStream fis = new FileInputStream(file)) {
            visitor = loadReferenceModel(fis);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            log.error("Error loading reference model", ioe);
            throw new RuntimeException("Error loading reference model", ioe);
        }
        return visitor;
    }

    public ReferenceModelVisitor loadReferenceModel(InputStream inputStream) {
        ReferenceModelVisitor visitor = new ReferenceModelVisitor();
        try {
            ANTLRInputStream input = new ANTLRInputStream(inputStream);
            odinLexer lexer = new odinLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            odinParser parser = new odinParser(tokens);
            ParseTree tree = parser.odin_text();
            visitor.visit(tree);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            log.error("Error loading reference model", ioe);
            throw new RuntimeException("Error loading reference model", ioe);
        }
        return visitor;
    }
}
