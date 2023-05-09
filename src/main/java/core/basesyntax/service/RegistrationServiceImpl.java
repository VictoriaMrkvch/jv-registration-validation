package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exception.RegistrationException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final int MIN_AGE = 18;
    private static final int MIN_LENGTH = 6;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new RegistrationException("User can't be null");
        }
        if (user.getLogin() == null) {
            throw new RegistrationException("Login can't be null");
        }
        if (user.getPassword() == null) {
            throw new RegistrationException("Password can't be null");
        }
        if (user.getAge() < MIN_AGE) {
            throw new RegistrationException("User must be at least " + MIN_AGE + " years old");
        }
        if (storageDao.get(user.getLogin()) != null) {
            throw new RegistrationException("Login is used");
        }
        if (user.getLogin().length() < MIN_LENGTH) {
            throw new RegistrationException("Login must have at least "
                    + MIN_LENGTH + " characters");
        }
        if (user.getPassword().length() < MIN_LENGTH) {
            throw new RegistrationException("Password must have at least "
                    + MIN_LENGTH + " characters");
        }
        return storageDao.add(user);
    }
}
