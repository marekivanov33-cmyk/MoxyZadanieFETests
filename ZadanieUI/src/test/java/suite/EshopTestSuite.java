package suite;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("FE_Tests")
@IncludeTags("UITest")
public class EshopTestSuite {
}
