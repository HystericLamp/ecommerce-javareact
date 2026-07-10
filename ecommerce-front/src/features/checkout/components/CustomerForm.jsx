import { Button } from "@/components/ui/button";
import { Card } from "@/components/ui/card";
import { Input } from "@/components/ui/input";

export default function CustomerForm({
  customer,
  onChange,
  onFillDemo
}) {
  return (
    <Card
      data-testid="customer-form-root"
      className="p-6 rounded-xl space-y-6"
    >
      <h2 className="text-lg font-semibold">
        Customer Details
      </h2>

      <p className="text-sm text-muted-foreground">
        This is a demo checkout. Please use test or dummy information, no real data is stored.
      </p>

      <Button
        type="button"
        variant="outline"
        onClick={onFillDemo}
      >
        Use Demo Information
      </Button>

      <div className="grid md:grid-cols-2 gap-4">
        <Input
          data-testid="form-firstname"
          name="firstName"
          placeholder="First Name"
          value={customer.firstName}
          onChange={onChange}
          required
        />

        <Input
          data-testid="form-lastname"
          name="lastName"
          placeholder="Last Name"
          value={customer.lastName}
          onChange={onChange}
        />

        <Input
          data-testid="form-email"
          name="email"
          placeholder="Email Address"
          value={customer.email}
          onChange={onChange}
          className="md:col-span-2"
          required
        />

        <Input
          data-testid="form-address1"
          name="addressLine1"
          placeholder="Address Line 1"
          value={customer.addressLine1}
          onChange={onChange}
          className="md:col-span-2"
        />

        <Input
          data-testid="form-address2"
          name="addressLine2"
          placeholder="Address Line 2"
          value={customer.addressLine2}
          onChange={onChange}
          className="md:col-span-2"
        />

        <Input
          data-testid="form-city"
          name="city"
          placeholder="City"
          value={customer.city}
          onChange={onChange}
        />

        <Input
          data-testid="form-province"
          name="provinceState"
          placeholder="Province / State"
          value={customer.provinceState}
          onChange={onChange}
        />

        <Input
          data-testid="form-postalcode"
          name="postalCode"
          placeholder="Postal Code"
          value={customer.postalCode}
          onChange={onChange}
        />

        <Input
          data-testid="form-country"
          name="country"
          placeholder="Country"
          value={customer.country}
          onChange={onChange}
        />
      </div>
    </Card>
  );
}