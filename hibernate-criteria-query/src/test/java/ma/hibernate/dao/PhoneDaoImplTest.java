package ma.hibernate.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ma.hibernate.model.Phone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PhoneDaoImplTest extends AbstractTest {
    private static final String PATH_TO_PHONE_DAO_IMPL_JAVA_CLASS =
            "src/main/java/ma/hibernate/dao/PhoneDaoImpl.java";
    private static final int MAX_NUMBER_OF_FOR_LOOPS_IN_FIND_ALL_METHOD = 2;
    private static String findAllMethodContent;
    private Map<String, String[]> params;

    @BeforeClass
    public static void readFindAllMethodContent() throws IOException {
        String phoneDaoImplContent = Files.readString(Paths.get(PATH_TO_PHONE_DAO_IMPL_JAVA_CLASS));
        int indexOfCreateMethod = phoneDaoImplContent.indexOf("public Phone create");
        int indexOfFindAllMethod = phoneDaoImplContent.indexOf("public List<Phone> findAll");
        if (indexOfCreateMethod < indexOfFindAllMethod) {
            findAllMethodContent = phoneDaoImplContent.substring(indexOfFindAllMethod);
        } else {
            findAllMethodContent = phoneDaoImplContent.substring(0, indexOfCreateMethod);
        }
    }

    @Before
    public void setUp() {
        params = new HashMap<>();
    }

    @Override
    protected Class<?>[] entities() {
        return new Class[]{Phone.class};
    }

    @Test
    public void findAll_switch_case_notOk() {
        boolean isSolutionWithoutSwitchCase = !findAllMethodContent.contains("switch")
                && !findAllMethodContent.contains("case");
        Assert.assertTrue(
                "In your solution you shouldn't use switch case",
                isSolutionWithoutSwitchCase
        );
    }

    @Test
    public void findAll_if_else_notOk() {
        boolean isSolutionWithoutIfElse = !findAllMethodContent.contains("if (")
                && !findAllMethodContent.contains("else {");
        Assert.assertTrue(
                "In your solution you shouldn't use if else",
                isSolutionWithoutIfElse
        );
    }

    @Test
    public void findAll_more_than_two_for_loops_notOk() {
        boolean isSolutionWithTwoForLoops = findAllMethodContent.split("for \\(").length <= 3;
        Assert.assertTrue(
                "In your solution you shouldn't use more than "
                    + MAX_NUMBER_OF_FOR_LOOPS_IN_FIND_ALL_METHOD
                    + " for loops",
                isSolutionWithTwoForLoops
        );
    }

    @Test
    public void create_Ok() {
        PhoneDao phoneDao = new PhoneDaoImpl(getSessionFactory());
        insertPhones(phoneDao);
    }

    @Test
    public void findAll_EmptyParams() {
        PhoneDao phoneDao = new PhoneDaoImpl(getSessionFactory());
        insertPhones(phoneDao);
        Assert.assertTrue(params.isEmpty());
        List<Phone> actual = phoneDao.findAll(params);
        Assert.assertNotNull(actual);
        Assert.assertEquals(8, actual.size());
    }

    @Test
    public void findAll_byOneMaker() {
        PhoneDao phoneDao = new PhoneDaoImpl(getSessionFactory());
        insertPhones(phoneDao);
        Assert.assertTrue(params.isEmpty());
        params.put("maker", new String[]{"Apple"});
        List<Phone> actualApplePhones = phoneDao.findAll(params);
        Assert.assertNotNull(actualApplePhones);
        Assert.assertEquals(2, actualApplePhones.size());

        params = new HashMap<>();
        params.put("maker", new String[]{"Samsung"});
        List<Phone> actualSamsungPhones = phoneDao.findAll(params);
        Assert.assertNotNull(actualSamsungPhones);
        Assert.assertEquals(4, actualSamsungPhones.size());

        params = new HashMap<>();
        params.put("maker", new String[]{"Oppo"});
        List<Phone> actualOppoPhones = phoneDao.findAll(params);
        Assert.assertNotNull(actualOppoPhones);
        Assert.assertEquals(1, actualOppoPhones.size());

        params = new HashMap<>();
        params.put("maker", new String[]{"Xiaomi"});
        List<Phone> actualXiaomiPhones = phoneDao.findAll(params);
        Assert.assertNotNull(actualXiaomiPhones);
        Assert.assertEquals(1, actualXiaomiPhones.size());
    }

    @Test
    public void findAll_bySeveralMakers() {
        PhoneDao phoneDao = new PhoneDaoImpl(getSessionFactory());
        insertPhones(phoneDao);
        Assert.assertTrue(params.isEmpty());
        params.put("maker", new String[]{"Apple", "Samsung"});
        List<Phone> actualAppleAndSamsungPhones = phoneDao.findAll(params);
        Assert.assertNotNull(actualAppleAndSamsungPhones);
        Assert.assertEquals(6, actualAppleAndSamsungPhones.size());

        params = new HashMap<>();
        params.put("maker", new String[]{"Apple", "Xiaomi"});
        List<Phone> actualAppleAndXiaomiPhones = phoneDao.findAll(params);
        Assert.assertNotNull(actualAppleAndXiaomiPhones);
        Assert.assertEquals(3, actualAppleAndXiaomiPhones.size());

        params = new HashMap<>();
        params.put("maker", new String[]{"Apple", "Xiaomi", "Oppo"});
        List<Phone> actualAppleAndXiaomiAndOppoPhones = phoneDao.findAll(params);
        Assert.assertNotNull(actualAppleAndXiaomiAndOppoPhones);
        Assert.assertEquals(4, actualAppleAndXiaomiAndOppoPhones.size());
    }

    @Test
    public void findAll_byTwoMakersAndSeveralColors() {
        PhoneDao phoneDao = new PhoneDaoImpl(getSessionFactory());
        insertPhones(phoneDao);
        Assert.assertTrue(params.isEmpty());

        params.put("color", new String[]{"red", "black"});
        params.put("maker", new String[]{"Apple", "Samsung"});
        List<Phone> actualAppleAndSamsungPhones = phoneDao.findAll(params);
        Assert.assertNotNull(actualAppleAndSamsungPhones);
        Assert.assertEquals(3, actualAppleAndSamsungPhones.size());

        params = new HashMap<>();
        params.put("color", new String[]{"white", "black"});
        params.put("maker", new String[]{"Apple", "Xiaomi", "Oppo"});
        List<Phone> actualAppleAndXiaomiAndOppoPhones = phoneDao.findAll(params);
        Assert.assertNotNull(actualAppleAndXiaomiAndOppoPhones);
        Assert.assertEquals(3, actualAppleAndXiaomiAndOppoPhones.size());

        params = new HashMap<>();
        params.put("color", new String[]{"yellow", "green"});
        params.put("maker", new String[]{"Apple", "Xiaomi", "Oppo"});
        List<Phone> notExistedColors = phoneDao.findAll(params);
        Assert.assertNotNull(notExistedColors);
        Assert.assertEquals(0, notExistedColors.size());

        params = new HashMap<>();
        params.put("color", new String[]{"yellow", "red"});
        params.put("maker", new String[]{"Apple", "Xiaomi", "Oppo"});
        List<Phone> oneExistedColor = phoneDao.findAll(params);
        Assert.assertNotNull(oneExistedColor);
        Assert.assertEquals(1, oneExistedColor.size());
    }

    @Test
    public void findAll_byTwoMakersAndSeveralColorsAndSeveralCountries() {
        PhoneDao phoneDao = new PhoneDaoImpl(getSessionFactory());
        insertPhones(phoneDao);
        Assert.assertTrue(params.isEmpty());

        params.put("color", new String[]{"red", "black"});
        params.put("maker", new String[]{"Apple", "Samsung"});
        params.put("countryManufactured", new String[]{"USA"});
        List<Phone> actualAppleAndSamsungPhones = phoneDao.findAll(params);
        Assert.assertNotNull(actualAppleAndSamsungPhones);
        Assert.assertEquals(1, actualAppleAndSamsungPhones.size());

        params = new HashMap<>();
        params.put("color", new String[]{"white"});
        params.put("maker", new String[]{"Apple", "Xiaomi", "Oppo"});
        params.put("countryManufactured", new String[]{"China"});
        List<Phone> actualAppleAndXiaomiAndOppoPhones = phoneDao.findAll(params);
        Assert.assertNotNull(actualAppleAndXiaomiAndOppoPhones);
        Assert.assertEquals(1, actualAppleAndXiaomiAndOppoPhones.size());

        params = new HashMap<>();
        params.put("color", new String[]{"white"});
        params.put("maker", new String[]{"Apple", "Xiaomi", "Oppo"});
        params.put("countryManufactured", new String[]{"China", "USA"});
        List<Phone> actualAppleAndXiaomiAndOppoPhonesChinaUsa = phoneDao.findAll(params);
        Assert.assertNotNull(actualAppleAndXiaomiAndOppoPhonesChinaUsa);
        Assert.assertEquals(2, actualAppleAndXiaomiAndOppoPhonesChinaUsa.size());
    }

    @Test
    public void findAll_byModel() {
        PhoneDao phoneDao = new PhoneDaoImpl(getSessionFactory());
        insertPhones(phoneDao);
        Assert.assertTrue(params.isEmpty());

        params.put("model", new String[]{"A5", "Oppo10", "WrongModel", "WrongModel"});
        List<Phone> actual = phoneDao.findAll(params);
        Assert.assertNotNull(actual);
        Assert.assertEquals(2, actual.size());

        params = new HashMap<>();
        params.put("model", new String[]{"A7"});
        List<Phone> actualA7 = phoneDao.findAll(params);
        Assert.assertNotNull(actualA7);
        Assert.assertEquals(3, actualA7.size());
    }

    private void insertPhones(PhoneDao phoneDao) {
        Phone iphoneX = (Phone) Phones.iphoneX.clone();
        verifyCreatePhoneWorks(phoneDao, iphoneX, 1L);

        Phone iphone7 = (Phone) Phones.iphone7.clone();
        verifyCreatePhoneWorks(phoneDao, iphone7, 2L);

        Phone samsungA5 = (Phone) Phones.samsungA5.clone();
        verifyCreatePhoneWorks(phoneDao, samsungA5, 3L);

        Phone samsungA7White = (Phone) Phones.samsungA7White.clone();
        verifyCreatePhoneWorks(phoneDao, samsungA7White, 4L);

        Phone samsungA7Red = (Phone) Phones.samsungA7Red.clone();
        verifyCreatePhoneWorks(phoneDao, samsungA7Red, 5L);

        Phone samsungA7Black = (Phone) Phones.samsungA7Black.clone();
        verifyCreatePhoneWorks(phoneDao, samsungA7Black, 6L);

        Phone oppo10white = (Phone) Phones.oppo10white.clone();
        verifyCreatePhoneWorks(phoneDao, oppo10white, 7L);

        Phone xiaomiRedmi5 = (Phone) Phones.xiaomiRedmi5.clone();
        verifyCreatePhoneWorks(phoneDao, xiaomiRedmi5, 8L);
    }

    private void verifyCreatePhoneWorks(PhoneDao phoneDao, Phone phone, Long expectedId) {
        Phone actual = phoneDao.create(phone);
        Assert.assertNotNull("Check you have implemented the `create` method "
                + "in the PhoneDaoImpl class", actual);
        Assert.assertNotNull("ID for phone should be autogenerated", actual.getId());
        Assert.assertEquals(expectedId, actual.getId());
        Assert.assertEquals(phone.getModel(), actual.getModel());
        Assert.assertEquals(phone.getCountryManufactured(), actual.getCountryManufactured());
    }

}
