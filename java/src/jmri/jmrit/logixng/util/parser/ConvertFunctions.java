package jmri.jmrit.logixng.util.parser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jmri.JmriException;
import jmri.util.TypeConversionUtil;

import org.openide.util.lookup.ServiceProvider;

/**
 * Implementation of conversion functions.
 * 
 * @author Daniel Bergqvist 2020
 */
@ServiceProvider(service = FunctionFactory.class)
public class ConvertFunctions implements FunctionFactory {

    @Override
    public Set<Function> getFunctions() {
        Set<Function> functionClasses = new HashSet<>();
        functionClasses.add(new IntFunction());
        functionClasses.add(new StrFunction());
        return functionClasses;
    }
    
    
    
    public static class IntFunction implements Function {

        @Override
        public String getName() {
            return "int";
        }

        @Override
        public Object calculate(List<ExpressionNode> parameterList) throws JmriException {
            if (parameterList.size() != 1) {
                throw new WrongNumberOfParametersException(Bundle.getMessage("WrongNumberOfParameters2", getName(), 1));
            }
            return (int) TypeConversionUtil.convertToLong(parameterList.get(0).calculate());
        }
        
    }
    
    public static class StrFunction implements Function {

        @Override
        public String getName() {
            return "str";
        }

        @Override
        public Object calculate(List<ExpressionNode> parameterList) throws JmriException {
            switch (parameterList.size()) {
                case 1:
                    return TypeConversionUtil.convertToString(parameterList.get(0).calculate(), false);
                case 2:
                    boolean do_i18n = TypeConversionUtil.convertToBoolean(parameterList.get(0).calculate(), false);
                    return TypeConversionUtil.convertToString(parameterList.get(0).calculate(), do_i18n);
                default:
                    throw new WrongNumberOfParametersException(Bundle.getMessage("WrongNumberOfParameters2", getName(), 1));
            }
        }
        
    }
    
}
